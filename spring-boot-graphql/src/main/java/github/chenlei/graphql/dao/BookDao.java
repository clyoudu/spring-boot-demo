package github.chenlei.graphql.dao;

import github.chenlei.graphql.entity.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author leichen
 * @date 2020/7/28 5:45 下午
 * @since
 */
@Repository
public class BookDao {

    private static final Map<String, Book> BOOKS = Collections.synchronizedMap(new LinkedHashMap<>());

    public Book add(Book book){
        book.setId(UUID.randomUUID().toString());
        BOOKS.put(book.getId(), book);
        return book;
    }

    public Book delete(String id) {
        return BOOKS.remove(id);
    }

    public Book selectOne(String id) {
        return BOOKS.get(id);
    }

    public Book update(Book book) {
        return BOOKS.computeIfPresent(book.getId(), (k, v) -> book);
    }

    public List<Book> query(String keyword) {
        if(StringUtils.isNotBlank(keyword)) {
            return BOOKS.values().stream().filter(book -> book.getName().contains(keyword)).collect(Collectors.toList());
        }
        return new ArrayList<>(BOOKS.values());
    }

}
