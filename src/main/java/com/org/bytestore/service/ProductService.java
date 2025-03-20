package com.org.bytestore.service;

import com.org.bytestore.audit.logbuilder.ProductLogBuilder;
import com.org.bytestore.mapper.request.AddProductRequestToProductMapper;
import com.org.bytestore.mapper.request.UpdateProductRequestToProductMapper;
import com.org.bytestore.mapper.response.ProductToAddProductResponseMapper;
import com.org.bytestore.mapper.response.ProductToGetAllProductsResponseMapper;
import com.org.bytestore.mapper.response.ProductToUpdateProductResponseMapper;
import com.org.bytestore.model.entity.Product;
import com.org.bytestore.model.request.DeleteProductRequest;
import com.org.bytestore.model.request.GetAllProductsRequest;
import com.org.bytestore.model.request.ProductModifyRequest;
import com.org.bytestore.model.request.UpdateProductRequest;
import com.org.bytestore.model.response.AddProductResponse;
import com.org.bytestore.model.response.DeleteProductResponse;
import com.org.bytestore.model.response.GetAllProductsResponse;
import com.org.bytestore.model.response.UpdateProductResponse;
import com.org.bytestore.model.validation.ValidationResult;
import com.org.bytestore.repository.ProductRepository;
import com.org.bytestore.validator.request.RequestAuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;

    private final AddProductRequestToProductMapper addProductRequestToProductMapper;

    private final ProductToAddProductResponseMapper productToAddProductResponseMapper;

    private final UpdateProductRequestToProductMapper updateProductRequestToProductMapper;

    private final ProductToUpdateProductResponseMapper productToUpdateProductResponseMapper;

    private final ProductToGetAllProductsResponseMapper productToGetAllProductsResponseMapper;

    private final RequestAuthValidator requestAuthValidator;

    private final ProductLogBuilder logBuilder;

    public Optional<AddProductResponse> addProduct(@RequestBody ProductModifyRequest request) {
        try {
            final ValidationResult validationResult = requestAuthValidator.validate(request);
            if (validationResult.getIsValid()) {
                final Product product = addProductRequestToProductMapper.map(request);
                productRepo.save(product);
                logBuilder.buildAndPublish(request);
                final AddProductResponse response = productToAddProductResponseMapper.map(product);
                return Optional.of(response);
            }
            return Optional.of(AddProductResponse.builder().message(validationResult.getValidationFailureReason()).build());
        } catch (final Exception err) {
            log.error("Unable to add product for {}, failed with exception", request, err);
        }
        return Optional.empty();
    }

    public Optional<UpdateProductResponse> updateProduct(@RequestBody UpdateProductRequest request) {
        try {
            final ValidationResult validationResult = requestAuthValidator.validate(request.getProductModifyRequest());
            if (validationResult.getIsValid()) {
                final Product product = updateProductRequestToProductMapper.map(request);
                productRepo.save(product);
                logBuilder.buildAndPublish(request);
                final UpdateProductResponse response = productToUpdateProductResponseMapper.map(product);
                return Optional.of(response);
            }
            return Optional.of(UpdateProductResponse.builder().message(validationResult.getValidationFailureReason()).build());
        } catch (final Exception err) {
            log.error("Unable to update product for {}, failed with exception", request, err);
        }
        return Optional.empty();
    }

    public Optional<DeleteProductResponse> deleteProduct(@RequestBody DeleteProductRequest request) {
        try {
            final Optional<Product> product = productRepo.findById(request.getProductId());
            if (product.isPresent()) {
                final Product deletedPRoduct = product.get();
                productRepo.delete(deletedPRoduct);
                logBuilder.buildAndPublish(request);
                return Optional.of(DeleteProductResponse.builder()
                        .message("Product Deleted Successfully...")
                        .success(Boolean.TRUE)
                        .build());
            }
            return Optional.of(DeleteProductResponse.builder()
                    .success(Boolean.FALSE)
                    .build());
        } catch (final Exception err){
            log.error("Unable to delete a product for {}, failed with exception",request,err);
        }
        return Optional.empty();
    }

                                                         // To-Do Add validation
    public Optional<GetAllProductsResponse> getAllProducts(@RequestBody GetAllProductsRequest request) {
        Optional<List<Product>> products = productRepo.findByOrgTokenId(request.getOrgTokenId());
        if (products.isPresent()) {
            final GetAllProductsResponse response = productToGetAllProductsResponseMapper.map(products.get());
            return Optional.of(response);
        }
        return Optional.of(GetAllProductsResponse.builder().products(List.of()).build());
    }
}
