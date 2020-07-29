package github.chenlei.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import github.chenlei.graphql.dao.BookDao;
import github.chenlei.graphql.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leichen
 * @date 2020/7/28 6:20 下午
 * @since
 */
@Component
public class BookMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private BookDao bookDao;

    public Book add(Book book) {
        return bookDao.add(book);
    }

    public Book delete(String id) {
        return bookDao.delete(id);
    }

    public Book update(Book book) {
        return bookDao.update(book);
    }

}
