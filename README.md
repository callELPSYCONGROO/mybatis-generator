# mybatis-generator
##一、使用Java代码配置mybatis-generator生成映射

项目例子的配置文件说明（可根据个人情况进行修改）：

mybatis-generator配置文件在：test/aurora.xml

启动Test文件在：test/org/dev/mybatis/generator/Aurora.java


根据test/aurora.xml中的说明进行配置，在test/aurora.xml中修改对应配置文件路径，启动Test即可。


##二、使用Maven插件mybatis-generator-maven-plugin生成映射

####1.在Maven中添加插件配置：

    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.35</version>
            </dependency>
        </dependencies>
        <configuration>
             <!--配置文件的路径-->
             <configurationFile>${basedir}/src/main/resources/generatorConfig.xml</configurationFile> 
             <overwrite>true</overwrite>
        </configuration>
    </plugin>
    
####2.配置generatorConfig.xml文件。

####3.运行插件生成映射
#####可以使用以下两种方式：
#####⑴.使用maven代码运行插件，到当前项目根目录下运行代码：
    mvn mybatis-generator:generate 
#####⑵.如果使用IDE工具，直接在工具里面运行即可
