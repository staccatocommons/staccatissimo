/*
Copyright (c) 2012, The Staccato-Commons Team
This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation; version 3 of the License.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.
*/
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lang.predicate.internal.TopLevelPredicate;

import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.WordUtils;


def stringMethodsFor = { 
  it.declaredMethods 
}

def classNameForFunctionLikeMethod = { 
  method, functionLikeType ->  "Apache${StringUtils.capitalize(method.name)}${functionLikeType}" 
}

def wrapPrimitive = {
  switch(it)  {
    case { !it.primitive }:
      return it
    case Integer.TYPE:
      return Integer
    default:
      throw new IllegalArgumentException("unsupported primitive ${it}")
  }
}

def predicateTemplate = {
  def className = classNameForFunctionLikeMethod(it, 'Predicate')
  """
  /**
   * @see StringUtils#${it.name}(String)
   */
  @NullSafe
  public static Predicate<String> ${it.name}() {
    class ${className} extends TopLevelPredicate<String> {
      private static final long serialVersionUID = 1L;

      public boolean eval(String arg) {
        return StringUtils.${it.name}(arg);
      }
    }
    return new ${className}();
  }
"""}


def functionTemplate = {
  def className = classNameForFunctionLikeMethod(it, 'Function')
  def returnType = wrapPrimitive(it.returnType).simpleName
"""
  /**
   * @see StringUtils#${it.name}(String)
   */
  @NullSafe
  public static Function<String, ${returnType}> ${it.name}() {
    class ${className} extends TopLevelFunction<String, ${returnType}> {
      private static final long serialVersionUID = 1L;
      public ${returnType} apply(String arg) {
        return StringUtils.${it.name}(arg);
      }
    }
    return new ${className}();
  }
""" }

def toFunctionLike = {
  if (it.returnType == Boolean.TYPE)
    predicateTemplate(it)
  else 
    functionTemplate(it)  
}

def generateSource =  { outputDir, generatedClassName, targetApacheClass ->
  new File(outputDir, "${generatedClassName}.java").withWriter { out ->
    out << """/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

/////////////////////////////////////////////////
///// WARNING: GENERATE CODE. DO NOT EDIT ///////
/////////////////////////////////////////////////

package net.sf.staccatocommons.util;

import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lang.function.internal.TopLevelFunction;
import net.sf.staccatocommons.lang.predicate.internal.TopLevelPredicate;

import org.apache.commons.lang.StringUtils;

/***
 * String {@link Function}s and {@link Predicate}s based on Apache {@link StringUtils}. 
 * They provide a 1-1 mapping to that utility class. 
 */
public final class ${generatedClassName} {

${stringMethodsFor(targetApacheClass)
    .findAll { it.parameterTypes == [String] }
    .collect { toFunctionLike(it) }
    .join("\n")} 
}
"""
  }
}

def outputDir = new File("target/generated-sources/net/sf/staccatocommons/util/")
outputDir.mkdirs()

generateSource outputDir, 'ApacheStrings', StringUtils
//generateSource outputDir, 'ApacheWords', WordUtils

