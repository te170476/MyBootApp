package jp.te4a.spring.boot.myapp10;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBean {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id ;
  @Column(nullable = false)
  private String title;
  private String writter;
  private String publisher;
  private Integer price;
}
