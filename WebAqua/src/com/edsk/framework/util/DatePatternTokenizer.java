package com.edsk.framework.util;

/**
 * DatePatternTokenizer.java<br>
 * DateSelect.jsp���� �� Locale�� Date ǥ�� ������ �м��ϱ� ����
 * ����ϴ� Utility Class.<br>
 *<br>
 * History :<br>
 * - 1.1 ��,��,�� �� ���� �ϵ��� ����. �۸��� 2003/03/06<br>
 *
 *@author �۸���
 *@version 1.1
 */
public class DatePatternTokenizer {
  String str;
  int index,l;
  final static char[] keyword ={'G','y','M','d','G','z','h','H','m','s'};
  final static char[] ignore = {'S','E','D','F','w','W','a','k','K'};

  public boolean hasMoreTokens() {
    int i=index;
    while (i<l && isIgnore(str.charAt(i))) i++;
    return (i<l);
  }

  private boolean isIn(char[] charSet,char key) {
    for (int i=0;i<charSet.length;i++) {
      if (charSet[i]==key) return true;
    }
    return false;
  }

  protected boolean isKeyword(char c) {
    return isIn(keyword,c);
  }

  protected boolean isIgnore(char c) {
    return isIn(ignore,c);
  }

  protected boolean isQuot(char c) {
    return (c=='\'');
  }

  public boolean isKeyword() {
    if (index<l) {
      char c=str.charAt(index);
      return isKeyword(c);
    }
    return false;
  }

  public boolean isIgnore() {
    if (index<l) {
      char c=str.charAt(index);
      return isIgnore(c);
    }
    return false;
  }

  public boolean isQuot() {
    if (index<l) {
        char c=str.charAt(index);
        return isQuot(c);
    }
    return false;
  }

  protected String getToken(char key) {
    StringBuffer sb=new StringBuffer();
    sb.append(key); index++;
    if (hasMoreTokens()) {
      while (index<l) {
        if (str.charAt(index)==key) sb.append(str.charAt(index++));
        else break;
      }
    }
    return sb.toString();
  }

  protected String getToken() {
    StringBuffer sb=new StringBuffer();
    while (index<l) {
      char c=str.charAt(index);
      if (!isKeyword(c) && !isIgnore(c) && c!='\'') {
        index++;
        sb.append(c);
      }
      else break;
    }
    return sb.toString();
  }

  protected String getQuotedString() {
    StringBuffer sb=new StringBuffer();
    if (str.charAt(index)=='\'') {
      index++;
      while (index<l) {
        char c=str.charAt(index++);
        if (c=='\'') {
          if (index<l && str.charAt(index)=='\'') index++;
          else break;
        }
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public String nextToken() {
    String token=null;
    while (index<l) {
      char c=str.charAt(index);
      if (isKeyword(c)) {token=getToken(c); break;}
      else if (isQuot(c)) {token=getQuotedString(); break;}
      else if (isIgnore(c)) {getToken(c);}
      else {token=getToken(); break;}
    }
    return token;
  }

  public DatePatternTokenizer(String str) {
    this.str=str;
    this.index=0;
    this.l=((str==null)?0:str.length());
  }
}