package nwh.legendkeeper.world;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Class used to set type references for 
 * test coverage
 * 
 * @author Nathanial.Heard
 *
 * @param <G> is the generic type provided during use
 */
public class ResponsePageImpl<G> extends PageImpl <G> {

	private static final long serialVersionUID = 7707713470477574207L;

	public ResponsePageImpl() {
        super(new ArrayList<G>());
    }

    public ResponsePageImpl(List<G> content) {
        super(content);
    }

    public ResponsePageImpl(List<G> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
    
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResponsePageImpl(@JsonProperty("content") List<G> content,
                        @JsonProperty("number") int number,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") Long totalElements,
                        @JsonProperty("pageable") JsonNode pageable,
                        @JsonProperty("last") boolean last,
                        @JsonProperty("totalPages") int totalPages,
                        @JsonProperty("sort") JsonNode sort,
                        @JsonProperty("first") boolean first,
                        @JsonProperty("numberOfElements") int numberOfElements) {
 
        super(content, PageRequest.of(number, size), totalElements);
    }
}
