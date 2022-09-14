package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        StringSplitter splitter = new StringSplitter();
        MethodSignature methodSignature = null;
        String methodName = "";
        String returnType = "";
        String accessModifier = null;
        List<String> argumentType = new ArrayList<>();
        List<String> argumentName = new ArrayList<>();
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        StringTokenizer tokens = new StringTokenizer(signatureString, "(");
        String namePart = tokens.nextToken();
        String argumentPart = tokens.nextToken();

        List<String> namePartList = splitter.splitByDelimiters(namePart, Arrays.asList(" "));
        List<String> argumentPartList = splitter.splitByDelimiters(argumentPart, Arrays.asList(" ", ", ",")"));

        if(namePartList.size()==3){
            accessModifier = namePartList.get(0);
            returnType = namePartList.get(1);
            methodName = namePartList.get(2);
        }else if(namePartList.size()==2){
            returnType = namePartList.get(0);
            methodName = namePartList.get(1);
        }

        if(argumentPartList.size()>0){
            for (int i = 1; i <= argumentPartList.size(); i++) {
                if(i%2==0){
                    argumentName.add(argumentPartList.get(i-1));
                }else{
                    argumentType.add(argumentPartList.get(i-1));
                }
            }

            for (int i = 0; i < argumentType.size(); i++) {
                arguments.add(new MethodSignature.Argument(argumentType.get(i), argumentName.get(i)));
            }
        }

        if(accessModifier == null){
            if(argumentPartList.size()>0){
                methodSignature = new MethodSignature(methodName, arguments);
            }else{
                methodSignature = new MethodSignature(methodName);
            }
        }else{
            if(argumentPartList.size()>0){
                methodSignature = new MethodSignature(methodName, arguments);
            }else{
                methodSignature = new MethodSignature(methodName);
            }
            methodSignature.setAccessModifier(accessModifier);
        }
        methodSignature.setReturnType(returnType);

        return methodSignature;

    }
}
