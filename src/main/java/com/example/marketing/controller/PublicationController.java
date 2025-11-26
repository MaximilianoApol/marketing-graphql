package com.example.marketing.controller;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import com.example.marketing.service.PublicationService; // Only necessary service dependency
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publications") // Standardized base path
@Tag(name = "Publications", description = "API for managing Publications and generating alerts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;

    // =======================================================
    // === CRUD Endpoints for Publication Management =======
    // =======================================================

    @Operation(summary = "Create a new publication")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Publication created"),
        @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    @PostMapping
    public ResponseEntity<PublicationResponseDTO> createPublication(@Valid @RequestBody PublicationRequestDTO requestDTO) {
        PublicationResponseDTO createdPublication = publicationService.create(requestDTO);
        return new ResponseEntity<>(createdPublication, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a publication by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publication found"),
        @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PublicationResponseDTO> getPublicationById(@PathVariable Integer id) {
        PublicationResponseDTO publication = publicationService.findById(id);
        return ResponseEntity.ok(publication);
    }

    @Operation(summary = "Get all publications with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of publications"),
        @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    @GetMapping
    public ResponseEntity<Page<PublicationResponseDTO>> getAllPublications(Pageable pageable) {
        Page<PublicationResponseDTO> publications = publicationService.getAllPublications(pageable);
        return ResponseEntity.ok(publications);
    }

    @Operation(summary = "Update an existing publication")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publication updated"),
        @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PublicationResponseDTO> updatePublication(@PathVariable Integer id, @Valid @RequestBody PublicationRequestDTO requestDTO) {
        PublicationResponseDTO updatedPublication = publicationService.update(id, requestDTO);
        return ResponseEntity.ok(updatedPublication);
    }

    @Operation(summary = "Delete a publication by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Publication deleted"),
        @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Integer id) {
        publicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // =======================================================
    // === Endpoints for Alert Generation ====================
    // =======================================================

    @Operation(summary = "Find content with viral potential")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed"),
        @ApiResponse(responseCode = "404", description = "No publications matching the criteria were found.")
    })
    @GetMapping("/alerts/viral-potential")
    public ResponseEntity<List<PublicationResponseDTO>> findPotentialViralContent() {
        List<PublicationResponseDTO> publications = publicationService.findPotentialViralContent();
        return ResponseEntity.ok(publications);
    }

}