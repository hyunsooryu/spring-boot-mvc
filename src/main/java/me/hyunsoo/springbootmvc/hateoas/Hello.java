package me.hyunsoo.springbootmvc.hateoas;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@Builder
public class Hello extends RepresentationModel<Hello> {

    private String prefix;

    private String name;

}
