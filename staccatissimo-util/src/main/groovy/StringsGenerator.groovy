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

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringEscapeUtils;
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
    case Character.TYPE:
      return Character
    default:
      throw new IllegalArgumentException("unsupported primitive ${it}")
  }
}

def predicateTemplate = { it, targetClassName ->
  def className = classNameForFunctionLikeMethod(it, 'Predicate')
  """
  /**
   * @see ${targetClassName}#${it.name}(String)
   */
  @NullSafe
  public static Predicate<String> ${it.name}() {
    class ${className} extends TopLevelPredicate<String> {
      private static final long serialVersionUID = 1L;

      public boolean eval(String arg) {
        return ${targetClassName}.${it.name}(arg);
      }
    }
    return new ${className}();
  }
"""}


def functionTemplate = { it, targetClassName ->
  def className = classNameForFunctionLikeMethod(it, 'Function')
  def returnType = wrapPrimitive(it.returnType).simpleName
"""
  /**
   * @see ${targetClassName}#${it.name}(String)
   */
  @NullSafe
  public static Function<String, ${returnType}> ${it.name}() {
    class ${className} extends TopLevelFunction<String, ${returnType}> {
      private static final long serialVersionUID = 1L;
      public ${returnType} apply(String arg) {
        return ${targetClassName}.${it.name}(arg);
      }
    }
    return new ${className}();
  }
""" }

def toFunctionLike = { it, targetClassName ->
  if (it.returnType == Boolean.TYPE)
    predicateTemplate(it, targetClassName)
  else 
    functionTemplate(it, targetClassName)  
}

def generateSource =  { outputDir, generatedClassName, targetApacheClass ->
  def targetClassName = targetApacheClass.simpleName
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

package net.sf.staccatocommons.util.apache;

import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lang.function.internal.TopLevelFunction;
import net.sf.staccatocommons.lang.predicate.internal.TopLevelPredicate;

import org.apache.commons.lang.${targetClassName};

/***
 * {@link Function}s and {@link Predicate}s based on Apache {@link ${targetClassName}}. 
 * They provide a 1-1 mapping to that utility class. 
 */
public final class ${generatedClassName} {

${stringMethodsFor(targetApacheClass)
    .findAll { it.parameterTypes == [String] }
    .collect { toFunctionLike(it, targetClassName) }
    .join("\n")} 
}
"""
  }
}

def outputDir = new File("target/generated-sources/net/sf/staccatocommons/util/apache")
outputDir.mkdirs()

generateSource outputDir, 'Strings', StringUtils
generateSource outputDir, 'Words', WordUtils
generateSource outputDir, 'Escapes', StringEscapeUtils
generateSource outputDir, 'Chars', CharUtils

