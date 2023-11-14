package com.nft.reservation.domain.concert;

import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Image;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.domain.image.ImageStore;
import com.nft.reservation.domain.image.UploadImage;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertForm;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import com.nft.reservation.domain.mapper.ReservationMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final JdbcConcertRepository repository;

    private final ReservationMapper mapper;

    private final ImageStore imageStore;

    @Override
    public ConcertDTO createConcert(ConcertForm concertForm) {
        // ConcertForm에서 공연이름 가져와서 공연장 조회
        /**
         * 1. ConcertForm에서 공연이름 가져와서 공연장 조회
         * 2. 공연장 이름이 있을 경우, 해당 공연장 ID를 Concert의 FK에 저장
         * 2-2. 없을 경우, 새로 등록 후, 해당 공연장 ID를 Concert의 FK에 저장
         * 3. ConcertForm에서 관람등급 내용 가져와서 관람등급 조회
         * 4. 관람 등급이 있을 경우, 해당 관람등급 ID를 Concert의 FK에 저장
         * 4-2. 없을 경우, 새로 등록 후, 해당 관람등급 ID를 Concert의 FK에 저장
         */
        Concert concert = mapper.concertDTOToEntity(concertForm.getConcertDTO());
        // 1. ConcertForm에서 공연이름 가져와서 공연장 조회
        String toFindHallName = concertForm.getConcertDTO().getHallName();
        Long findHallId = repository.findConcertHallIdByName(toFindHallName);
        if (findHallId == null) {
            // 2-2. 없을 경우, 새로 등록 후, 해당 공연장 ID를 Concert의 FK에 저장
            //Jdbc Template의 Key Holder 사용
            ConcertHallDTO concertHallDTO = new ConcertHallDTO();
            concertHallDTO.setName(concertForm.getConcertDTO().getHallName());
            concertHallDTO.setCapacity(concertForm.getHallCapacity());
            concertHallDTO.setRowSize(concertForm.getHallRow());
            concertHallDTO.setColumnSize(concertForm.getHallColumn());
            concertHallDTO.setAddress(concertForm.getConcertDTO().getHallAddress());
            log.info("입력한 콘서트 주소={}", concertForm.getConcertDTO().getHallAddress());

            Long saveHallId = repository.saveHall(concertHallDTO);
            concert.setHallId(saveHallId);
        } else {
            // 2. 공연장 이름이 있을 경우, 해당 공연장 ID를 Concert의 FK에 저장
            concert.setHallId(findHallId);
        }
        //  3. ConcertForm에서 관람등급 내용 가져와서 관람등급 조회
        Long findRankId = repository.findRankIdByDetail(concertForm.getConcertDTO().getRate());
        if (findRankId == null) {
            // 4-2. 없을 경우, 새로 등록 후, 해당 관람등급 ID를 Concert의 FK에 저장
            Long saveRankId = repository.saveRank(concertForm.getConcertDTO().getRate());
            concert.setRankId(saveRankId);
        } else {
            // 4. 관람 등급이 있을 경우, 해당 관람등급 ID를 Concert의 FK에 저장
            concert.setRankId(findRankId);
        }

        log.info("공연 DTO에서 변환된 공연 엔티티 = {}", concert);

        // 공연 엔티티 DB에 저장
        // 반환된 공연 ID 가져오기
        Long saveConcertId = repository.saveConcert(concert);

        // 5. 공연 폼에서 이미지를 가져와서 파일 저장
        // 썸네일, 캐러셀, 본문 사진 전부 List<UploadImage>로 변환하기
        MultipartFile thumbnailImage = concertForm.getThumbnailImage();
        MultipartFile carouselImage = concertForm.getCarouselImage();
        List<MultipartFile> contentImages = concertForm.getContentImages();

        ArrayList<MultipartFile> saveImages = new ArrayList<>(contentImages);
        saveImages.add(thumbnailImage);
        saveImages.add(carouselImage);

        List<UploadImage> uploadImages = null;
        try {
            uploadImages = imageStore.storeImages(saveImages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 6. 변환된 UUID와 경로를 저장
        for (UploadImage uploadImage : uploadImages) {
            Image image = new Image(
                    uploadImage.getUploadFileName(),
                    uploadImage.getStoreFileName(),
                    saveConcertId);

            // Image 저장 메서드 구현
            repository.saveImage(image);
        }

        return mapper.concertToConcertResponseDTO(concert);
        // HTML로 테스트 이후 리팩토링
        // 다운로드 구현
    }

    @Override
    public ConcertDTO getConcertDetail(Integer concertId) {
        // 컨트롤러에게 전달받은 콘서트 ID로 DAO에 find 메서드 조회
        Optional<Concert> findConcert = repository.findById(concertId);
        // 컨트롤러에서 반환하기 위해 필요한 정보들만 담아서 반환 (DTO 객체)
        if (findConcert.isPresent()) {
            // Optional 객체가 null이 아닐 경우, DTO로 변환해서 반환
            Concert concert = findConcert.get();
            return mapper.concertToConcertResponseDTO(concert);
        }
        return null;
    }

    @Override
    public List<SeatDTO> getConcertSeat(Integer concertId) {
        List<Seat> bookedSeats = repository.findBookedSeatById(concertId);

        List<SeatDTO> dtoBookedSeats = new ArrayList<>();
        for (Seat bookedSeat : bookedSeats) {

            SeatDTO seatDTO = mapper.seatToSeatResponseDTO(bookedSeat);
            dtoBookedSeats.add(seatDTO);
        }

        return dtoBookedSeats;
    }

    @Override
    public ConcertHallDTO getConcertHallSize(Integer concertId) {
        // repository에서 해당 공연 ID와 연결된 공연장 정보 가져오기
        Optional<ConcertHall> concertHallById = repository.findConcertHallById(
                Long.valueOf(concertId));
        // ConcertHall 엔티티 -> DTO로 변환
        if (concertHallById.isPresent()) {
            // Optional 객체가 null이 아닐 경우, DTO로 변환해서 반환
            ConcertHall concertHall = concertHallById.get();
            return mapper.concertHallToConcertHallDTO(concertHall);
        }
        return null;
    }
}
