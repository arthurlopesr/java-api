package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.domain.entities.jpa.UserTypeEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserRepository;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import com.client.ws.rasmooplus.useCases.UserUseCase;
import com.client.ws.rasmooplus.useCases.factory.UserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserUseCaseImpl implements UserUseCase {

    private static final String PNG = ".png";

    private static final String JPEG = ".jpeg";

    private static final String WEBP = ".webp";

    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;

    UserUseCaseImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    public UserEntity create(UserDTO userDTO) {
        if (Objects.nonNull((userDTO.getUserId()))) {
            throw new BadRequestException("User ID most be null");
        }

        var userTypeOpt = userTypeRepository.findById(userDTO.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoundException("userTypeId not found");
        }

        UserTypeEntity userType = userTypeOpt.get();
        UserEntity user = UserFactory.fromDtoToEntity(userDTO, userType, null);
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return getUserBYId(id);
    }

    public UserEntity update(Long id, UserDTO userDTO) {
        getUserBYId(id);
        userDTO.setUserId(id);
        var userTypeOpt = userTypeRepository.findById(userDTO.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoundException("userTypeId not found");
        }

        UserTypeEntity userType = userTypeOpt.get();
        return userRepository.save(UserFactory.fromDtoToEntity(userDTO, userType, null));
    }

    public void delete(Long id) {
        getUserBYId(id);
        userRepository.deleteById(id);
    }

    public UserEntity uploadPhoto(Long id, MultipartFile file) throws IOException {
        String imgName = file.getOriginalFilename();
        String formatPNG = imgName.substring(imgName.length() - 4);
        String formatJPEG = imgName.substring(imgName.length() - 5);
        String formatWEBP = imgName.substring(imgName.length() - 5);

        if (!(PNG.equalsIgnoreCase(formatPNG) || JPEG.equalsIgnoreCase(formatJPEG) || WEBP.equalsIgnoreCase(formatWEBP))) {
            throw new BadRequestException("Invalid image format");
        }

        UserEntity user = getUserBYId(id);
        user.setPhotoName(file.getOriginalFilename());
        user.setPhoto(file.getBytes());
        return userRepository.save(user);
    }

    @Override
    public byte[] downloadPhoto(Long id) {
        UserEntity user = findById(id);

        if (Objects.isNull(user.getPhoto())) {
            throw new BadRequestException("User don't have photo");
        }

        return user.getPhoto();
    }

    private UserEntity getUserBYId(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isEmpty()) {
            throw new NotFoundException("UserEntity not found");
        }
        return optionalUserEntity.get();
    }
}
