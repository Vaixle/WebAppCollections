package com.petushkov.webappcollections.mappers;

import com.petushkov.webappcollections.dto.RefreshTokenDto;
import com.petushkov.webappcollections.models.RefreshToken;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RefreshTokenMapper {
    RefreshToken refreshTokenDtoToRefreshToken(RefreshTokenDto refreshTokenDto);

    RefreshTokenDto refreshTokenToRefreshTokenDto(RefreshToken refreshToken);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RefreshToken updateRefreshTokenFromRefreshTokenDto(RefreshTokenDto refreshTokenDto, @MappingTarget RefreshToken refreshToken);
}
