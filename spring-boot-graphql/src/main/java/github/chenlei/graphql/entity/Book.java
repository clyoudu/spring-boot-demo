package github.chenlei.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leichen
 * @date 2020/7/28 5:36 下午
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String name;
    private String author;
    private int pages;
    private boolean available;
    private double price;

}
