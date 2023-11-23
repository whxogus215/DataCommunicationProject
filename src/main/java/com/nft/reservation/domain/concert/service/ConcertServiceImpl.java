package com.nft.reservation.domain.concert.service;

import com.nft.reservation.domain.concert.repository.JdbcConcertRepository;
import com.nft.reservation.domain.concert.entity.Concert;
import com.nft.reservation.domain.concert.entity.ConcertHall;
import com.nft.reservation.domain.concert.entity.Image;
import com.nft.reservation.domain.concert.entity.Seat;
import com.nft.reservation.domain.image.ImageSorter;
import com.nft.reservation.domain.image.ImageStore;
import com.nft.reservation.domain.image.UploadImage;
import com.nft.reservation.domain.utils.TokenCreator;
import com.nft.reservation.web.concert.dto.ConcertDTO;
import com.nft.reservation.web.concert.dto.ConcertForm;
import com.nft.reservation.web.concert.dto.ConcertHallDTO;
import com.nft.reservation.web.concert.dto.SeatDTO;
import com.nft.reservation.domain.mapper.ReservationMapper;
import com.nft.reservation.web.concert.dto.SeatResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final JdbcConcertRepository repository;
    private final ReservationMapper mapper;
    private final ImageStore imageStore;
    private final ImageSorter imageSorter;

    @Transactional
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

        // 공연 엔티티 DB에 저장
        // 반환된 공연 ID 가져오기
        Long saveConcertId = repository.saveConcert(concert);
        concert.setId(saveConcertId);

        // 5. 공연 폼에서 이미지를 가져와서 파일 저장
        // 썸네일, 캐러셀, 본문 사진 전부 List<UploadImage>로 변환하기
        MultipartFile thumbnailImage = concertForm.getThumbnailImage();
        MultipartFile carouselImage = concertForm.getCarouselImage();

        List<MultipartFile> contentImages = concertForm.getContentImages();
        log.info("업로드 될 본문 이미지={}", contentImages.get(0));

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
        if (uploadImages.isEmpty()) {
            return mapper.concertToConcertResponseDTO(concert);
        }
        for (UploadImage uploadImage : uploadImages) {
            if (uploadImage == null) {
                continue;
            }
            Image image = new Image(
                    uploadImage.getUploadFileName(),
                    uploadImage.getStoreFileName(),
                    saveConcertId);

            // Image 저장 메서드 구현
            repository.saveImage(image);
        }

        log.info("생성된 콘서트 엔티티={}", concert);

        return mapper.concertToConcertResponseDTO(concert);
    }

    @Override
    public ConcertDTO getConcertDetail(Long concertId) {
        // 컨트롤러에게 전달받은 콘서트 ID로 DAO에 find 메서드 조회
        Optional<ConcertDTO> findConcert = repository.findConcertById(concertId);

        if (findConcert.isEmpty()) {
            return null;
        }
        ConcertDTO concertDTO = findConcert.get();

        // 해당 ID로 이미지 리스트 조회
        List<Image> findImages = repository.findImageByConcertId(concertId);
        if (findImages.isEmpty()) {
            return concertDTO;
        }

        Image thumbNail = ImageSorter.getThumbNail(findImages);
        Image carousel = ImageSorter.getCarousel(findImages);

        List<Image> contents = ImageSorter.getContents(findImages);
        List<String> contentUrls = contents.stream()
                .map(Image::getStoreName)
                .toList();

        concertDTO.setThumbnailUrl(thumbNail.getStoreName());
        concertDTO.setCarouselUrl(carousel.getStoreName());
        concertDTO.setImages(contentUrls);

        // 컨트롤러에서 반환하기 위해 필요한 정보들만 담아서 반환 (DTO 객체)
        // Optional 객체가 null이 아닐 경우, DTO로 변환해서 반환
        return concertDTO;
    }

    @Override
    public List<SeatDTO> getConcertSeat(Long concertId) {
        List<Seat> bookedSeats = repository.findBookedSeatById(concertId);

        List<SeatDTO> dtoBookedSeats = new ArrayList<>();
        for (Seat bookedSeat : bookedSeats) {

            SeatDTO seatDTO = mapper.seatToSeatResponseDTO(bookedSeat);
            dtoBookedSeats.add(seatDTO);
        }

        return dtoBookedSeats;
    }

    @Override
    @Transactional
    public SeatResponse reserveSeats(Long concertId, List<SeatDTO> seatDTOs) {
        List<SeatDTO> reservedSeats = new ArrayList<>();

        // seatDTOs에 있는 좌석들 Update 및 좌석 반환 받기
        for (SeatDTO seatDTO : seatDTOs) {
            SeatDTO savedSeat = repository.saveSeatById(concertId, seatDTO);
            savedSeat.setData(true); // 저장된 좌석은 예약 됐음(true)으로 변경
            reservedSeats.add(savedSeat);
        }
        SeatResponse seatResponse = new SeatResponse();
        seatResponse.setReservedSeats(reservedSeats);

        Long currentMintCount = repository.getMintCount();

        String tokenValue = TokenCreator.createMintToken(currentMintCount + 1);
        seatResponse.setCreatedToken(tokenValue);

        repository.updateMintCount(currentMintCount + 1);
        return seatResponse;
    }

    @Override
    public ConcertHallDTO getConcertHallSize(Long concertId) {
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

    @Override
    public Resource getDownloadImage(String fileName) throws MalformedURLException {
        return new UrlResource("file:" + imageStore.getFullPath(fileName));
    }
}
