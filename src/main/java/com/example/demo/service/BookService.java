package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Book;

import java.util.List;

public interface BookService {
    public void save(Book book);
    public void delete(Long id);
    public Book get(Long id);
    public List<Book> list();
}
