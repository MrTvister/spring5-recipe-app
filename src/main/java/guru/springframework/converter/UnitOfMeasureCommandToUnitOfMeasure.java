package guru.springframework.converter;


import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeashure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeashure>{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeashure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeashure uom = new UnitOfMeashure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }
}