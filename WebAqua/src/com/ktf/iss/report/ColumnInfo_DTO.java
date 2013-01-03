package com.ktf.iss.report;

public class ColumnInfo_DTO
{
  private String col_name;
  private String alias;
  private String data_type;
  private String len;
  private String description;

  public String getAlias()
  {
    return this.alias;
  }

  public void setAlias(String alias)
  {
    this.alias = alias;
  }

  public String getCol_name()
  {
    return this.col_name;
  }

  public void setCol_name(String col_name)
  {
    this.col_name = col_name;
  }

  public String getData_type()
  {
    return this.data_type;
  }

  public void setData_type(String data_type)
  {
    this.data_type = data_type;
  }

  public String getDescription()
  {
    return this.description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getLen()
  {
    return this.len;
  }

  public void setLen(String len)
  {
    this.len = len;
  }
}