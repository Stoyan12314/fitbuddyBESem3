//package org.example.controller;
//
//import org.example.buisness.ExerciseManager;
//import org.example.buisness.impl.CloudinaryManagerImpl;
//import org.example.controller.dto.*;
//import org.example.domain.Exercise;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ExerciseControllerTest {
//    @Mock
//    private ExerciseManager exerciseManager;
//    @Mock
//    private CloudinaryManagerImpl cloudinary;
//
//    @InjectMocks
//    private ExerciseController exerciseController;
//
////    @Test
////    @DisplayName("Should return an error when the request is invalid")
////    void updateExerciseWhenRequestIsInvalidThenReturnError() {
////        MockMultipartFile file =
////                new MockMultipartFile("file", "test.jpg", "image/jpeg", "test".getBytes());
////
////
////        UpdateExerciseRequest request = new UpdateExerciseRequest();
////        request.setId(1L);
////        request.setName("");
////        request.setDescription("");
////
////
////        ResponseEntity<UpdateExerciseResponse> responseEntity =
////                exerciseController.updateExercise(1L, request, file);
////
////
////        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
////    }
//
////    @Test
////    @DisplayName("Should return an error when the file is not an image")
////    void
////    updateExerciseWhenFileIsNotImageThenReturnError() { // create a mock MultipartFile
////        // object with a non-image file
////        MockMultipartFile file =
////                new MockMultipartFile("file", "test.txt", "text/plain", "test file".getBytes());
////
////        // create a mock UpdateExerciseRequest object
////        UpdateExerciseRequest request = new UpdateExerciseRequest();
////        request.setId(1L);
////        request.setName("Test Exercise");
////        request.setDescription("Test Description");
////        request.setImageUrl("test-image-url");
////
////        // call the updateExercise method with the mock objects
////        ResponseEntity<UpdateExerciseResponse> responseEntity =
////                exerciseController.updateExercise(1L, request, file);
////
////        // verify that the exerciseManager and cloudinary methods were not called
////        verify(exerciseManager, never()).getExercise(anyLong());
////        verify(exerciseManager, never()).updateExercise(any(Exercise.class));
////        verify(cloudinary, never()).uploadImage(any(MultipartFile.class));
////
////        // verify that the response entity has a status of BAD_REQUEST
////        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
////    }
//
////    @Test
////    @DisplayName("Should return an error when the exercise with the given ID does not exist")
////    void updateExerciseWhenExerciseIdDoesNotExistThenReturnError() {
////        MockMultipartFile file =
////                new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image".getBytes());
////
////
////        UpdateExerciseRequest updateExerciseRequest = new UpdateExerciseRequest();
////        updateExerciseRequest.setId(1L);
////        updateExerciseRequest.setName("Test Exercise");
////        updateExerciseRequest.setDescription("Test Exercise Description");
////        updateExerciseRequest.setImageUrl("test-image-url");
////
////
////        when(exerciseManager.getExercise(updateExerciseRequest.getId())).thenReturn(null);
////
////
////        ResponseEntity<UpdateExerciseResponse> responseEntity =
////                exerciseController.updateExercise(
////                        updateExerciseRequest.getId(), updateExerciseRequest, file);
////
////
////        verify(exerciseManager, times(1)).getExercise(updateExerciseRequest.getId());
////
////        verify(exerciseManager, never()).updateExercise(any(Exercise.class));
////
////        verify(cloudinary, never()).uploadImage(any(MultipartFile.class));
////
////        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
////    }
//
////    @Test
////    @DisplayName("Should update the exercise with new data and image when the request is valid")
////    void updateExerciseWithNewDataAndImageWhenRequestIsValid() { // create mock MultipartFile
////        MockMultipartFile file =
////                new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image".getBytes());
////
////        // create mock UpdateExerciseRequest
////        UpdateExerciseRequest updateExerciseRequest = new UpdateExerciseRequest();
////        updateExerciseRequest.setId(1L);
////        updateExerciseRequest.setName("New Exercise Name");
////        updateExerciseRequest.setDescription("New Exercise Description");
////        updateExerciseRequest.setImageUrl("http://localhost:8080/images/test.jpg");
////
////        // create mock Exercise
////        Exercise exercise =
////                Exercise.builder()
////                        .id(1L)
////                        .name("Old Exercise Name")
////                        .description("Old Exercise Description")
////                        .imageUrl("http://localhost:8080/images/old.jpg")
////                        .build();
////
////        // mock exerciseManager.getExercise
////        when(exerciseManager.getExercise(1L)).thenReturn(exercise);
////
////        // mock cloudinary.uploadImage
////        when(cloudinary.uploadImage(file)).thenReturn("http://localhost:8080/images/test.jpg");
////
////        // call updateExercise method
////        ResponseEntity<UpdateExerciseResponse> responseEntity =
////                exerciseController.updateExercise(1L, updateExerciseRequest, file);
////
////        // verify exerciseManager.updateExercise is called
////        verify(exerciseManager, times(1)).updateExercise(exercise);
////
////        // verify response status code is OK
////        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
////
////        // verify response body contains updated exercise id
////        assertEquals(1L, responseEntity.getBody().getId());
////    }
//
////    @Test
////    @DisplayName("Should return a bad request when the request is invalid")
////    void createExerciseWithInvalidRequest() {
////        CreateExerciseRequest invalidRequest =
////                CreateExerciseRequest.builder().name(null).description(null).build();
////
////        MultipartFile file = new MockMultipartFile("file", new byte[0]);
////
////        MethodArgumentNotValidException exception = assertThrows(
////                MethodArgumentNotValidException.class,
////                () -> {
////                    exerciseController.createExercise(invalidRequest, file);
////                });
////
////        BindingResult bindingResult = exception.getBindingResult();
////        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
////
////        assertEquals(2, fieldErrors.size());
////        assertEquals("Name cannot be blank", fieldErrors.get(0).getDefaultMessage());
////        assertEquals("Description cannot be blank", fieldErrors.get(1).getDefaultMessage());
////    }
//
//    @Test
//    @DisplayName("Should create an exercise with a valid request and file")
//    void createExerciseWithValidRequestAndFile() {
//        CreateExerciseRequest request =
//                CreateExerciseRequest.builder()
//                        .name("Push-ups")
//                        .description("Push-ups exercise")
//                        .build();
//
//        MockMultipartFile file =
//                new MockMultipartFile("file", "test.jpg", "image/jpeg", "test".getBytes());
//
//        when(cloudinary.uploadImage(file))
//                .thenReturn("https://res.cloudinary.com/test/image/upload/v1631234567/test.jpg");
//
//        when(exerciseManager.createExercise(any(Exercise.class))).thenReturn(1L);
//
//        ResponseEntity<CreateExerciseResponse> responseEntity =
//                exerciseController.createExercise(request, file);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//
//        assertNotNull(responseEntity.getBody());
//
//        assertEquals(1L, responseEntity.getBody().getId());
//
//
//        verify(exerciseManager, times(1))
//                .createExercise(
//                        argThat(
//                                exercise ->
//                                        exercise.getName().equals("Push-ups")
//                                                && exercise.getDescription()
//                                                .equals("Push-ups exercise")
//                                                && exercise.getImageUrl()
//                                                .equals(
//                                                        "https://res.cloudinary.com/test/image/upload/v1631234567/test.jpg")));
//    }
//
////    @Test
////    @DisplayName("Should return no content when updating a non-existing exercise")
////    void updateNonExistingExerciseReturnsNoContent() {
////        Long id = 1L;
////        UpdateExerciseRequest request = new UpdateExerciseRequest();
////        request.setId(id);
////        request.setName("Updated Exercise");
////        request.setDescription("Updated Exercise Description");
////        request.setImageUrl("https://updated-image-url.com");
////
////        when(exerciseManager.getExercise(id)).thenReturn(null);
////
////        ResponseEntity<Void> response = exerciseController.updateExercise(id, request);
////
////        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
////        verify(exerciseManager, times(1)).getExercise(id); // Verify that getExercise is called with the correct argument
////        verify(exerciseManager, times(0)).updateExercise(any(Exercise.class));
////    }
////    @Test
////    @DisplayName("Should update the exercise with the given id and request data")
////    void updateExerciseWithGivenIdAndRequestData() { // create mock objects
////        UpdateExerciseRequest request = new UpdateExerciseRequest();
////        request.setId(1L);
////        request.setName("Updated Exercise");
////        request.setDescription("Updated Exercise Description");
////        request.setImageUrl("http://updated-image-url.com");
////
////        Exercise exercise =
////                Exercise.builder()
////                        .id(request.getId())
////                        .name(request.getName())
////                        .description(request.getDescription())
////                        .imageUrl(request.getImageUrl())
////                        .build();
////
////        // mock exerciseManager.getExercise(id) method
////        when(exerciseManager.getExercise(request.getId())).thenReturn(exercise);
////
////        // mock cloudinary.uploadImage(file) method
////        when(cloudinary.uploadImage(any())).thenReturn(request.getImageUrl());
////
////        // call the method under test
////        ResponseEntity<Void> response = exerciseController.updateExercise(request.getId(), request);
////
////        // verify that exerciseManager.updateExercise(exercise) method is called once
////        verify(exerciseManager, times(1)).updateExercise(exercise);
////
////        // verify that ResponseEntity.noContent() method is called once
////        assertEquals(ResponseEntity.noContent(), response);
////    }
//
//    @Test
//    @DisplayName("Should return no content when there are no exercises available")
//    void getExercisesWhenNoExercisesAvailable() {
//        List<Exercise> emptyList = Collections.emptyList();
//        when(exerciseManager.getExercises()).thenReturn(emptyList);
//
//        ResponseEntity<GetExercisesResponse> responseEntity = exerciseController.getExercises();
//
//        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//        assertNull(responseEntity.getBody());
//        verify(exerciseManager, times(1)).getExercises();
//    }
//
//    @Test
//    @DisplayName("Should return a list of exercises when there are exercises available")
//    void getExercisesWhenExercisesAvailable() {
//        List<Exercise> exercises =
//                Collections.singletonList(
//                        Exercise.builder()
//                                .id(1L)
//                                .name("Push-ups")
//                                .imageUrl("https://example.com/push-ups.jpg")
//                                .description("Push-ups exercise description")
//                                .build());
//        when(exerciseManager.getExercises()).thenReturn(exercises);
//
//        ResponseEntity<GetExercisesResponse> responseEntity = exerciseController.getExercises();
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(exercises, responseEntity.getBody().getExercises());
//        verify(exerciseManager, times(1)).getExercises();
//    }
//
//    @Test
//    @DisplayName("Should return no content when the exerciseId is not found")
//    void getExerciseWhenExerciseIdNotFound() {
//        Exercise exercise = null;
//        when(exerciseManager.getExercise(anyLong())).thenReturn(exercise);
//
//        ResponseEntity<GetExerciseResponse> response = exerciseController.getExercise(1);
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        assertNull(response.getBody());
//        verify(exerciseManager, times(1)).getExercise(1L);
//    }
//
//    @Test
//    @DisplayName("Should return the exercise when the exerciseId is valid")
//    void getExerciseWhenExerciseIdIsValid() {
//        Exercise exercise =
//                Exercise.builder()
//                        .id(1L)
//                        .name("Push-ups")
//                        .imageUrl("https://example.com/push-ups.jpg")
//                        .description("Push-ups exercise description")
//                        .build();
//
//        when(exerciseManager.getExercise(anyLong())).thenReturn(exercise);
//
//        ResponseEntity<GetExerciseResponse> response = exerciseController.getExercise(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(exercise.getId(), response.getBody().getExercise().getId());
//        assertEquals(exercise.getName(), response.getBody().getExercise().getName());
//        assertEquals(exercise.getImageUrl(), response.getBody().getExercise().getImageUrl());
//        assertEquals(exercise.getDescription(), response.getBody().getExercise().getDescription());
//
//        verify(exerciseManager, times(1)).getExercise(anyLong());
//    }
//
//    @Test
//    @DisplayName("Should delete the exercise with the given ID")
//    void deleteExerciseWithGivenId() {
//        int exerciseId = 1;
//
//        ResponseEntity<Void> response = exerciseController.deleteExercise(exerciseId);
//
//        verify(exerciseManager, times(1)).deleteExerciseById(exerciseId);
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Should not delete the exercise if the ID is not found")
//    void deleteExerciseWhenIdNotFound() {
//        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
//                .when(exerciseManager).deleteExerciseById(anyLong());
//
//        assertThrows(ResponseStatusException.class, () -> exerciseController.deleteExercise(1));
//
//        verify(exerciseManager, times(1)).deleteExerciseById(1);
//    }
//}