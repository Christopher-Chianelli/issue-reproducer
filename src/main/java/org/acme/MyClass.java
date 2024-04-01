package org.acme;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;
import java.util.function.Consumer;

public class MyClass {
    private static final String className = "org.acme.GeneratedClass";
    private static final ClassLoader classLoader = new ClassLoader() {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (name.equals(className)) {
                byte[] bytecode = getClassBytecode();
                return defineClass(name, bytecode, 0, bytecode.length);
            }
            return super.loadClass(name);
        }
    };

    private static byte[] getClassBytecode() {
        String className = "org.acme.GeneratedClass";
        String internalClassName = className.replace('.', '/');
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classWriter.visit(Opcodes.V17, Modifier.PUBLIC, internalClassName, null, Type.getInternalName(Object.class), new String[] { Type.getInternalName(
                Consumer.class)});

        MethodVisitor methodVisitor = classWriter.visitMethod(Modifier.PUBLIC, "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE),
                false);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();

        methodVisitor = classWriter.visitMethod(Modifier.PUBLIC, "accept", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(
                Object.class)), null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, Type.getInternalName(String.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, internalClassName, "accept",
                Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String.class)), false);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();


        // Generates throw new RuntimeException(message)
        methodVisitor = classWriter.visitMethod(Modifier.PUBLIC, "accept", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String.class)), null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(Opcodes.NEW, Type.getInternalName(RuntimeException.class));
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(RuntimeException.class), "<init>",
                Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String.class)), false);
        methodVisitor.visitInsn(Opcodes.ATHROW);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();

        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
    public static Consumer<String> getConsumer() throws Throwable {
        @SuppressWarnings("unchecked")
        Class<? extends Consumer<String>> generatedClass = (Class<? extends Consumer<String>>) classLoader.loadClass(className);
        return generatedClass.getConstructor().newInstance();
    }
}
