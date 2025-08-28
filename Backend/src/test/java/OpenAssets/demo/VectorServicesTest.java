package OpenAssets.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import OpenAssets.demo.DTOs.VectorDTO;
import OpenAssets.demo.Entities.VectorEntity;
import OpenAssets.demo.Repositories.VectorsRepo;
import OpenAssets.demo.Services.VectorServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VectorServicesTest {

    @Mock
    private VectorsRepo vectorsRepo;

    @InjectMocks
    private VectorServices vectorServices;

    @BeforeEach
    void setUp() {
        // Mocking the @Value annotation
        ReflectionTestUtils.setField(vectorServices, "SAVE_LOCATION", "/test/path");
    }

    @Test
    void testFindBySearchTerm_ShouldReturnCorrectlyMappedDTOs() {
        // Arrange
        String searchTerm = "test";
        VectorEntity entity1 = new VectorEntity();
        entity1.setVectorId(101);
        entity1.setPreviewPath("previews/image1.jpg");

        VectorEntity entity2 = new VectorEntity();
        entity2.setVectorId(102);
        entity2.setPreviewPath("previews/image2.png");

        List<VectorEntity> mockEntities = Arrays.asList(entity1, entity2);

        when(vectorsRepo.findBySearchTerm(searchTerm)).thenReturn(mockEntities);

        // Act
        List<VectorDTO> result = vectorServices.findBySearchTerm(searchTerm);

        // Assert
        // 1. Verify the size of the returned list
        assertNotNull(result);
        assertEquals(2, result.size());

        // 2. Verify the content of the first DTO
        VectorDTO dto1 = result.get(0);
        assertEquals(101, dto1.getId());
        assertTrue(dto1.getPreviewPath().endsWith("/files/previews/image1.jpg"));

        // 3. Verify the content of the second DTO
        VectorDTO dto2 = result.get(1);
        assertEquals(102, dto2.getId());
        assertTrue(dto2.getPreviewPath().endsWith("/files/previews/image2.png"));

        // 4. Verify the correct repository method was called
        verify(vectorsRepo, times(1)).findBySearchTerm(searchTerm);
    }


    @Test
    void testGetAllPreviews_ShouldReturnCorrectlyMappedDTOs() {
        // Arrange
        VectorEntity entity1 = new VectorEntity();
        entity1.setVectorId(1);
        entity1.setPreviewPath("previews/image1.jpg");

        VectorEntity entity2 = new VectorEntity();
        entity2.setVectorId(2);
        entity2.setPreviewPath("previews/image2.jpg");

        List<VectorEntity> mockEntities = Arrays.asList(entity1, entity2);

        // Mock the repository to return a list of known entities
        when(vectorsRepo.findAll()).thenReturn(mockEntities);

        // Act
        List<VectorDTO> result = vectorServices.getAllPreviews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify the content of the first DTO
        assertEquals(1, result.get(0).getId());
        assertTrue(result.get(0).getPreviewPath().endsWith("/files/previews/image1.jpg"));

        // Verify the content of the second DTO
        assertEquals(2, result.get(1).getId());
        assertTrue(result.get(1).getPreviewPath().endsWith("/files/previews/image2.jpg"));

        // Verify that the findAll method was called
        verify(vectorsRepo, times(1)).findAll();
    }
}
