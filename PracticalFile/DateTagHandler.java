package com.viraj;

import java.util.Date;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;


public class DateTagHandler extends SimpleTagSupport {

  public void doTag() throws JspException {

      JspWriter out=getJspContext().getOut();

      try {

          out.println(new Date());

      } catch (java.io.IOException ex) {
          throw new JspException(ex.getMessage());
      }
  }
}