package excel  ;

import com4j.*;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface QueryTables extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   */

  @DISPID(148)
  @PropGet
  excel._Application getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   */

  @DISPID(149)
  @PropGet
  excel.XlCreator getCreator();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   */

  @DISPID(150)
  @PropGet
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   */

  @DISPID(118)
  @PropGet
  int getCount();


  /**
   * @param connection Mandatory java.lang.Object parameter.
   * @param destination Mandatory excel.Range parameter.
   * @param sql Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(181)
  excel._QueryTable add(
    java.lang.Object connection,
    excel.Range destination,
    @Optional java.lang.Object sql);


  /**
   * @param index Mandatory java.lang.Object parameter.
   */

  @DISPID(170)
  excel._QueryTable item(
    java.lang.Object index);


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @param index Mandatory java.lang.Object parameter.
   */

  @DISPID(0)
  @PropGet
  @DefaultMethod
  excel._QueryTable get_Default(
    java.lang.Object index);


  /**
   */

  @DISPID(-4)
  java.util.Iterator<Com4jObject> iterator();

  // Properties:
}
