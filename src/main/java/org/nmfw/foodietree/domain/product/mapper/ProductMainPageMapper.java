package org.nmfw.foodietree.domain.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.nmfw.foodietree.domain.product.dto.response.ProductDto;
import org.nmfw.foodietree.domain.product.dto.response.CategoryByFoodDto;

import java.util.List;

@Mapper
public interface ProductMainPageMapper {

    List<ProductDto> findAll();

    List<CategoryByFoodDto> categoryByFoodList(List<String> category);

}
