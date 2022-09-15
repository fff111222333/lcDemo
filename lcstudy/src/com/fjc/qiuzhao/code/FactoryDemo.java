package com.fjc.qiuzhao.code;

/**
 * author 凡
 * 简单工厂模式
 * @author Administrator
 */
public class FactoryDemo {
/*    //抽象工厂
    public abstract class AbstractFactory {
        public abstract <T extends Product>T createProduct(Class<T> clazz);
    }

    //具体工厂
    public class ConcreateFactory extends AbstractFactory {

        @Override
        public <T extends Product> T createProduct(Class<T> clazz) {
            Product product = null;
            try {
                product = (Product) Class.forName(clazz.getName()).newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return (T)product;
        }

    }

    //待生产的产品抽象类
    public abstract class Product {

        public void method1(){
            //具体方法
        }

        //抽象方法
        public abstract void method2();

    }

    //被生产的具体产品1
    public class ConcreateProduct1 extends Product {

        @Override
        public void method2() {
            System.out.println("我是concreteProduct1...");
        }

    }
    //被生产的具体产品2
    public class ConcreateProduct2 extends Product {

        @Override
        public void method2() {
            System.out.println("我是concreteProduct2...");
        }

    }

    //场景类
    public class Client {

        public static void main(String[] args) {

            AbstractFactory factory = new ConcreateFactory();

            ConcreateProduct1 product1 = factory.createProduct(ConcreateProduct1.class);
            product1.method2();

            ConcreateProduct2 product2 = factory.createProduct(ConcreateProduct2.class);
            product2.method2();

        }

    }*/

}
