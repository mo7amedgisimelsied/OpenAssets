package OpenAssets.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import OpenAssets.demo.DTOs.ModelDTO;
import OpenAssets.demo.Entities.ModelEntity;
import OpenAssets.demo.Repositories.ModelRepo;
import OpenAssets.demo.Services.ModelServices;
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
public class ModelServicesTest {

    @Mock
    private ModelRepo modelRepo;

    @InjectMocks
    private ModelServices modelServices;

    @BeforeEach
    void setUp() {
        // Mocking the @Value annotation
        ReflectionTestUtils.setField(modelServices, "SAVE_LOCATION", "/test/path");
    }

    @Test
    void testFindBySearchTerm_ShouldReturnCorrectlyMappedDTOs() {
        // Arrange
        String searchTerm = "test";
        ModelEntity entity1 = new ModelEntity();
        entity1.setModelId(101);
        entity1.setPreviewPath("previews/image1.jpg");

        ModelEntity entity2 = new ModelEntity();
        entity2.setModelId(102);
        entity2.setPreviewPath("previews/image2.png");

        List<ModelEntity> mockEntities = Arrays.asList(entity1, entity2);

        when(modelRepo.findBySearchTerm(searchTerm)).thenReturn(mockEntities);

        // Act
        List<ModelDTO> result = modelServices.findBySearchTerm(searchTerm);

        // Assert
        // 1. Verify the size of the returned list
        assertNotNull(result);
        assertEquals(2, result.size());

        // 2. Verify the content of the first DTO
        ModelDTO dto1 = result.get(0);
        assertEquals(101, dto1.getId());
        assertTrue(dto1.getPreviewPath().endsWith("/files/previews/image1.jpg"));

        // 3. Verify the content of the second DTO
        ModelDTO dto2 = result.get(1);
        assertEquals(102, dto2.getId());
        assertTrue(dto2.getPreviewPath().endsWith("/files/previews/image2.png"));

        // 4. Verify the correct repository method was called
        verify(modelRepo, times(1)).findBySearchTerm(searchTerm);
    }


    @Test
    void testGetAllPreviews_ShouldReturnCorrectlyMappedDTOs() {
        // Arrange
        ModelEntity entity1 = new ModelEntity();
        entity1.setModelId(1);
        entity1.setPreviewPath("previews/image1.jpg");

        ModelEntity entity2 = new ModelEntity();
        entity2.setModelId(2);
        entity2.setPreviewPath("previews/image2.jpg");

        List<ModelEntity> mockEntities = Arrays.asList(entity1, entity2);

        // Mock the repository to return a list of known entities
        when(modelRepo.findAll()).thenReturn(mockEntities);

        // Act
        List<ModelDTO> result = modelServices.getAllPreviews();

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
        verify(modelRepo, times(1)).findAll();
    }

}
