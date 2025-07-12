package com.vector.common.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 枚举字段序列化
 */
@NoArgsConstructor
@AllArgsConstructor
public class EnumDescSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private JsonEnumDesc annotation;
    private transient BeanProperty property;

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value);
        if (annotation == null) {
            return;
        }
        // 生成附加字段
        if (value instanceof Enum) {
            try {
                String enumFieldName = annotation.enumFieldName();
                Field field = value.getClass().getDeclaredField(enumFieldName);
                Assert.notNull(field, "枚举类" + value.getClass().getSimpleName() +
                        "未定义字段" + enumFieldName);
                field.setAccessible(true);
                String fieldValue = field.get(value).toString();

                String newFieldName = annotation.newFieldName();
                if (StringUtils.isBlank(newFieldName)) {
                    newFieldName = property.getName() + StringUtils.capitalize(field.getName());
                }
                gen.writeStringField(newFieldName, fieldValue);
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {
        JsonEnumDesc annotation = property.getAnnotation(JsonEnumDesc.class);
        if (annotation != null) {
            return new EnumDescSerializer(annotation, property);
        } else {
            return prov.findValueSerializer(property.getType(), property);
        }
    }
}
