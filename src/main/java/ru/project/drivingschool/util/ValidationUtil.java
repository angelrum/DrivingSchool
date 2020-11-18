package ru.project.drivingschool.util;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ru.project.drivingschool.model.HasId;
import ru.project.drivingschool.util.exception.IllegalRequestDataException;
import ru.project.drivingschool.util.exception.NotFoundException;

import java.util.List;

public class ValidationUtil {

    public final static long REGISTER_SEQ = 1000L;

    public final static long GLOBAL_SEQ = 10_000L;

    public static void assureIdConsistent(HasId bean, long id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    private ValidationUtil() {
    }

    public static void checkByRegisterId(long id) {
        checknotfoundwithid(id >= REGISTER_SEQ, id );
    }

    public static void checkByGlobalId(long id) {
        checknotfoundwithid(id >= GLOBAL_SEQ, id);
    }

    public static <T> T checkNotFoundWithId(T object, long id) {
        checknotfoundwithid(object != null, id);
        return object;
    }

    public static <T> T checkNotFoundWithId(T object, long companyId, long id) {
        checkNotFound(object!=null, String.format("id=%s companyId=%s", id, companyId));
        return object;
    }

    public static <T> List<T> checkNotFoundWithId(List<T> objects, long id) {
        checknotfoundwithid(!CollectionUtils.isEmpty(objects), id);
        return objects;
    }

    public static void checknotfoundwithid(boolean found, long id) {
        checkNotFound(found, String.format("id=%s", id));

    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static <T> void checkNotNull(T object) {
        Assert.notNull(object, String.format("%s, must not be null", object.getClass().getName()));
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }
}
