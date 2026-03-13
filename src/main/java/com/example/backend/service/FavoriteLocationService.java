package com.example.backend.service;

import com.example.backend.dto.FavoriteLocationRequest;
import com.example.backend.entity.FavoriteLocation;
import com.example.backend.repository.FavoriteLocationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteLocationService {

    private final FavoriteLocationRepository repository;

    public FavoriteLocationService(FavoriteLocationRepository repository) {
        this.repository = repository;
    }

    public String addFavorite(FavoriteLocationRequest request) {

        List<FavoriteLocation> existingLocations = repository.findLocation(
                request.getCityName(),
                request.getLatitude(),
                request.getLongitude()
        );

        if (!existingLocations.isEmpty()) {
            return "Location already exists in favorites.";
        }

        FavoriteLocation favoriteLocation = new FavoriteLocation();
        favoriteLocation.setCityName(request.getCityName());
        favoriteLocation.setLatitude(request.getLatitude());
        favoriteLocation.setLongitude(request.getLongitude());
        favoriteLocation.setCreatedAt(LocalDateTime.now());

        repository.save(favoriteLocation);

        return "Location added to favorites successfully.";
    }

    public List<FavoriteLocation> getAllFavorites() {
        return repository.findAll();
    }

    public String deleteFavorite(Long id) {
        if (!repository.existsById(id)) {
            return "Favorite location not found.";
        }

        repository.deleteById(id);
        return "Favorite location deleted successfully.";
    }
}