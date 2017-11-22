package com.chengzhi.scdp.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ObjectUtil {
	private static Logger logger = Logger.getLogger(ObjectUtil.class);
	
	/**
	 * 构造查询条件
	 * @param c
	 * @param cond
	 */
	public static void contructCond(Criteria c,Object cond){
		Map<String,Field> fieldMap = BaseDataCache.getClassFieldsMap(cond.getClass());
		for(Iterator<Field> it = fieldMap.values().iterator();it.hasNext();){
			Field field = it.next();
			Method method = BaseDataCache.getGetMethod(cond.getClass(), field.getName());
			if(method != null)
				setCondValue(method, field, cond, c);
		}
	}
	
	/**
	 * 设置查询条件
	 * @param method
	 * @param field
	 * @param cond
	 * @param c
	 */
	private static void setCondValue(Method method,Field field,Object cond,Criteria c){
		String type = field.getType().getName();
		try{
			String fieldName = field.getName().toLowerCase();
			Object obj = method.invoke(cond);
			if(obj == null || StringUtil.isNullOrEmpty(obj.toString()))
				return;
			
			if(fieldName.endsWith("notisnull")){
				if("Y".equalsIgnoreCase((String)obj)){
					String fieldNameNotIsNull = field.getName();
					String _fieldName  = fieldNameNotIsNull.substring(0, fieldNameNotIsNull.length()-9);
					c.add(Restrictions.isNotNull(_fieldName));
				}
			}
			else if(fieldName.endsWith("isnull") && !fieldName.endsWith("notisnull")){
				if("Y".equalsIgnoreCase((String)obj)){
					String fieldNameIsNull = field.getName();
					String _fieldName = fieldNameIsNull.substring(0, fieldNameIsNull.length()-6);
					c.add(Restrictions.isNull(_fieldName));
				}
			}
			else if("java.lang.String".equals(type)){
				String value = (String)obj;
				String _fieldName = field.getName();
				if(fieldName.endsWith("$like")){
					if(!value.startsWith("%"))
						value = "%" + value;
					if(!value.endsWith("%"))
						value = value + "%";
					c.add(Restrictions.like(_fieldName.substring(0, _fieldName.length()-5), value).ignoreCase());
				}
				else{
					c.add(Restrictions.eq(_fieldName, value).ignoreCase());
				}
			}
			else if("java.lang.Integer".equals(type)){
				c.add(Restrictions.eq(field.getName(), (Integer)obj));
			}
			else if("java.lang.Float".equals(type)){
				c.add(Restrictions.eq(field.getName(), (Float)obj));
			}
			else if("java.lang.Double".equals(type)){
				c.add(Restrictions.eq(field.getName(), (Double)obj));
			}
			else if("java.util.Date".equals(type)){
				String _fieldName = field.getName();
				if(fieldName.endsWith("1")){
//					c.add(Restrictions.ge(_fieldName.substring(0, _fieldName.length()-1),DateTimeUtil.getDayEarliest((Date)obj)));
					c.add(Restrictions.ge(_fieldName.substring(0, _fieldName.length()-1), (Date)obj));
				}
				else if(fieldName.endsWith("2")){
//					c.add(Restrictions.le(_fieldName.substring(0, _fieldName.length()-1),DateTimeUtil.getDayLatest((Date)obj)));
					c.add(Restrictions.le(_fieldName.substring(0, _fieldName.length()-1), (Date)obj));
				}
				else{
					c.add(Restrictions.eq(_fieldName, (Date)obj));
				}
			}
			else if("java.sql.Timestamp".equals(type)){
				String _fieldName = field.getName();
				if(fieldName.endsWith("1")){
					c.add(Restrictions.ge(_fieldName.substring(0, _fieldName.length()-1),DateTimeUtil.getDayEarliest((Timestamp)obj)));
				}
				else if(fieldName.endsWith("2")){
					c.add(Restrictions.le(_fieldName.substring(0, _fieldName.length()-1),DateTimeUtil.getDayLatest((Timestamp)obj)));
				}
				else{
					c.add(Restrictions.eq(_fieldName, (Timestamp)obj));
				}
			}
			else if("java.lang.Boolean".equals(type)){
				c.add(Restrictions.eq(field.getName(), (Boolean)obj));
			}
			else if("java.math.BigDecimal".equals(type)){
				String _fieldName = field.getName();
				if(fieldName.endsWith("1")){
					c.add(Restrictions.ge(_fieldName.substring(0, _fieldName.length()-1), (BigDecimal)obj));
				}
				else if(fieldName.endsWith("2")){
					c.add(Restrictions.le(_fieldName.substring(0, _fieldName.length()-1),(BigDecimal)obj));
				}
				else if(fieldName.endsWith("$notequal")){
					c.add(Restrictions.not(Restrictions.eq(_fieldName.substring(0, _fieldName.length()-9), (BigDecimal)obj)));
				}
				else{
					c.add(Restrictions.eq(_fieldName, (BigDecimal)obj));
				}
			}
			else if("java.lang.Long".equals(type)){
				String _fieldName = field.getName();
				if(fieldName.endsWith("1")){
					c.add(Restrictions.ge(_fieldName.substring(0, _fieldName.length()-1), (Long)obj));
				}
				else if(fieldName.endsWith("2")){
					c.add(Restrictions.le(_fieldName.substring(0, _fieldName.length()-1),(Long)obj));
				}
				else if(fieldName.endsWith("$notequal")){
					c.add(Restrictions.not(Restrictions.eq(_fieldName.substring(0, _fieldName.length()-9), (Long)obj)));
				}
				else{
					c.add(Restrictions.eq(_fieldName, (Long)obj));
				}
			}
			else if("[Ljava.lang.Long;".equalsIgnoreCase(type)){
				String _fieldName = field.getName();
				if(fieldName.endsWith("notin")){
					c.add(Restrictions.not(Restrictions.in(_fieldName.substring(0, _fieldName.length()-5), (Long[])obj)));
				}
				else if(fieldName.endsWith("in")){
					c.add(Restrictions.in(_fieldName.substring(0, _fieldName.length()-2), (Long[])obj));
				}
			}
			else if("[Ljava.lang.String".equalsIgnoreCase(type)){
				String _fieldName = field.getName();
				if(fieldName.endsWith("notin")){
					c.add(Restrictions.or(Restrictions.isNull(_fieldName.substring(0, _fieldName.length()-5))
							,Restrictions.not(Restrictions.in(_fieldName.substring(0, _fieldName.length()-5)
									, (String[])obj))));
				}
				else if(fieldName.endsWith("between")){
					c.add(Restrictions.between(_fieldName.substring(0, _fieldName.length()-7), ((String[])obj)[0], ((String[])obj)[1]));
				}
				else if(fieldName.endsWith("in")){
					c.add(Restrictions.in(_fieldName.substring(0, _fieldName.length()-2), (String[])obj));
				}
			}
			
		}catch(Exception e){
			StringUtil.writeStackTraceToLog(logger, e);
		}
	}
	
	/**
	 * 对象非空属性拷贝
	 * @param target 目标对象
	 * @param src 源对象
	 */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
