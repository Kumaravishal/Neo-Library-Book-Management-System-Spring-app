package com.examly.springapp;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddBook() throws Exception {
        String json = "{\"bookTitle\": \"Effective Java\", \"authorName\": \"Joshua Bloch\", \"genre\": \"Programming\", \"isbn\": \"9780134685991\", \"publisher\": \"Addison-Wesley\", \"yearPublished\": \"2018\"}";
        mockMvc.perform(post("/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/getAllBooks")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void test_Controller_Directory_Exists() {
        File directory = new File("src/main/java/com/examly/springapp/controller");
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    public void test_BookController_File_Exists() {
        File file = new File("src/main/java/com/examly/springapp/controller/BookController.java");
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    public void test_Model_Directory_Exists() {
        File directory = new File("src/main/java/com/examly/springapp/model");
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    public void test_Book_File_Exists() {
        File file = new File("src/main/java/com/examly/springapp/model/Book.java");
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    public void test_Repository_Directory_Exists() {
        File directory = new File("src/main/java/com/examly/springapp/repository");
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    public void test_Service_Directory_Exists() {
        File directory = new File("src/main/java/com/examly/springapp/service");
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    public void test_BookController_Class_Exists() {
        checkClassExists("com.examly.springapp.controller.BookController");
    }

    @Test
    public void test_BookRepo_Class_Exists() {
        checkClassExists("com.examly.springapp.repository.BookRepo");
    }

    @Test
    public void test_BookService_Class_Exists() {
        checkClassExists("com.examly.springapp.service.BookService");
    }

    @Test
    public void test_Book_Model_Class_Exists() {
        checkClassExists("com.examly.springapp.model.Book");
    }

    @Test
    public void test_Book_Model_Has_bookId_Field() {
        checkFieldExists("com.examly.springapp.model.Book", "bookId");
    }

    @Test
    public void test_Book_Model_Has_bookTitle_Field() {
        checkFieldExists("com.examly.springapp.model.Book", "bookTitle");
    }

    @Test
    public void test_Book_Model_Has_authorName_Field() {
        checkFieldExists("com.examly.springapp.model.Book", "authorName");
    }

    @Test
    public void test_Book_Model_Has_genre_Field() {
        checkFieldExists("com.examly.springapp.model.Book", "genre");
    }

    @Test
    public void test_Book_Model_Has_isbn_Field() {
        checkFieldExists("com.examly.springapp.model.Book", "isbn");
    }

    @Test
    public void test_Book_Model_Has_publisher_Field() {
        checkFieldExists("com.examly.springapp.model.Book", "publisher");
    }

    @Test
    public void test_Book_Model_Has_yearPublished_Field() {
        checkFieldExists("com.examly.springapp.model.Book", "yearPublished");
    }

    @Test
    public void test_BookRepo_Extends_JpaRepository() {
        checkClassImplementsInterface("com.examly.springapp.repository.BookRepo", "org.springframework.data.jpa.repository.JpaRepository");
    }

    @Test
    public void test_CorsConfiguration_Class_Exists() {
        checkClassExists("com.examly.springapp.configuration.CorsConfiguration");
    }

    @Test
    public void test_CorsConfiguration_Has_Configuration_Annotation() {
        checkClassHasAnnotation("com.examly.springapp.configuration.CorsConfiguration", "org.springframework.context.annotation.Configuration");
    }

    @Test
    public void test_InvalidIsbnException_Class_Exists() {
        checkClassExists("com.examly.springapp.exception.InvalidIsbnException");
    }

    @Test
    public void test_InvalidIsbnException_Extends_RuntimeException() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.exception.InvalidIsbnException");
            assertTrue(RuntimeException.class.isAssignableFrom(clazz),
                    "InvalidIsbnException should extend RuntimeException");
        } catch (ClassNotFoundException e) {
            fail("InvalidIsbnException class does not exist.");
        }
    }

    // ===== Helper methods =====

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interfaceClazz = Class.forName(interfaceName);
            assertTrue(interfaceClazz.isAssignableFrom(clazz));
        } catch (ClassNotFoundException e) {
            fail("Class or interface not found: " + className + ", " + interfaceName);
        }
    }

    private void checkClassHasAnnotation(String className, String annotationName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> annotationClazz = Class.forName(annotationName);
            assertTrue(clazz.isAnnotationPresent((Class<? extends java.lang.annotation.Annotation>) annotationClazz));
        } catch (ClassNotFoundException e) {
            fail("Annotation or class not found: " + className + ", " + annotationName);
        }
    }
}
