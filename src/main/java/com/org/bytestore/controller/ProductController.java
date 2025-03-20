package com.org.bytestore.controller;

import com.org.bytestore.model.request.DeleteProductRequest;
import com.org.bytestore.model.request.GetAllProductsRequest;
import com.org.bytestore.model.request.ProductModifyRequest;
import com.org.bytestore.model.request.UpdateProductRequest;
import com.org.bytestore.model.response.AddProductResponse;
import com.org.bytestore.model.response.DeleteProductResponse;
import com.org.bytestore.model.response.GetAllProductsResponse;
import com.org.bytestore.model.response.UpdateProductResponse;
import com.org.bytestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/byteStore/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Optional<AddProductResponse>> addProduct(@RequestBody ProductModifyRequest request){
        Optional<AddProductResponse> product = productService.addProduct(request);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/updateProduct")
    public ResponseEntity<Optional<UpdateProductResponse>> updateProduct(@RequestBody UpdateProductRequest request){
        Optional<UpdateProductResponse> updateProduct = productService.updateProduct(request);
        return ResponseEntity.ok(updateProduct);
    }

    @PostMapping("/getAllProducts")
    public ResponseEntity<Optional<GetAllProductsResponse>> getAllProducts(@RequestBody GetAllProductsRequest request){
        Optional<GetAllProductsResponse> getAllProducts = productService.getAllProducts(request);
        return ResponseEntity.ok(getAllProducts);
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<Optional<DeleteProductResponse>> deleteProduct(@RequestBody DeleteProductRequest request){
        Optional<DeleteProductResponse> deleteProduct = productService.deleteProduct(request);
        return ResponseEntity.ok(deleteProduct);
    }
}
