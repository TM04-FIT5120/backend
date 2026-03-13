package com.example.backend.controller;

import com.example.backend.dto.FavoriteLocationRequest;
import com.example.backend.entity.FavoriteLocation;
import com.example.backend.service.FavoriteLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteLocationController {

    private final FavoriteLocationService favoriteLocationService;

    public FavoriteLocationController(FavoriteLocationService favoriteLocationService) {
        this.favoriteLocationService = favoriteLocationService;
    }

    @PostMapping("/add")
    public String addFavorite(@RequestBody FavoriteLocationRequest request) {
        return favoriteLocationService.addFavorite(request);
    }

    @GetMapping("/all")
    public List<FavoriteLocation> getAllFavorites() {
        return favoriteLocationService.getAllFavorites();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFavorite(@PathVariable Long id) {
        return favoriteLocationService.deleteFavorite(id);
    }
}