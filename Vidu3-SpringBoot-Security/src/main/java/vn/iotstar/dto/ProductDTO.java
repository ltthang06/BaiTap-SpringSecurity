package vn.iotstar.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    private String imageUrl;

    private Long userId;

    private String username;

    private MultipartFile image;
}