package aprendendo.api.crudmongodb.common;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class Response<T> {
    
    private T object;

    private String message;
}
