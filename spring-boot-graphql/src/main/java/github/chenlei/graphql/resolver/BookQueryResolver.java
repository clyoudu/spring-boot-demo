package github.chenlei.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import github.chenlei.graphql.dao.BookDao;
import github.chenlei.graphql.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author leichen
 * @date 2020/7/28 6:16 下午
 * @since
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private BookDao bookDao;

    public Book book(String id) {
        return bookDao.selectOne(id);
    }

    public List<Book> query(String keyword) {
        return bookDao.query(keyword);
    }

}
