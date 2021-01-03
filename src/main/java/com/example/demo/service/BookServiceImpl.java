package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM book WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> list() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public Book get(Long id) {
        String sql = "SELECT * FROM book WHERE id=" + id;
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Book.class));    }
}
