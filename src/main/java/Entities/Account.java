package Entities;


import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class Account {
    @Id
    private String accId;
    private Status status;
    private String password;
    private Integer amount;
    private String branchName;
    private Integer foul;
    private String userNationalCode;


}
