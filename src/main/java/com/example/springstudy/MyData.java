package com.example.springstudy;



import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="mydata")
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column
    @NotEmpty(message = "공백 불가")
    private String name;

    @Column(length = 200,nullable = true)
    @Email(message = "메일 주소만")
    private String mail;

    @Column(nullable = true)
    @Min(value = 0,message = "0 이상")
    @Max(value = 200,message = "200 이하")
    private Integer age;

    @Column(nullable = true)
    private String memo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
