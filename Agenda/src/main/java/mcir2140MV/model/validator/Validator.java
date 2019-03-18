package mcir2140MV.model.validator;

import mcir2140MV.model.base.User;

import java.util.List;

public interface Validator<E>{

	void validate(E entity, List<String> list);
	
}
