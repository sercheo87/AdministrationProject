AdministrationProject
=====================

[![Join the chat at https://gitter.im/sercheo87/AdministrationProject](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/sercheo87/AdministrationProject?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Administration general of basic process for project.

#Configuration
- Change port jboss
- file: **jboss-as-7.1.1.Final\standalone\configuration\standalone.xml**
- Find ```<socket-binding name="http" port="1313"/>```
- For test run in browser [localhost:1313](http://localhost:1313/)

#Mysql
For add the conexion MySql follows step.  
1.Create the next path **JBOSS_HOME\modules\com\mysql\main**  
2.Create file **module.xml** with next content  
```xml
<?xml version="1.0" encoding="UTF-8"?>

<!--
  ~ JBoss, Home of Professional Open Source.
  ~ Copyright 2010, Red Hat, Inc., and individual contributors
  ~ as indicated by the @author tags. See the copyright.txt file in the
  ~ distribution for a full listing of individual contributors.
  ~
  ~ This is free software; you can redistribute it and/or modify it
  ~ under the terms of the GNU Lesser General Public License as
  ~ published by the Free Software Foundation; either version 2.1 of
  ~ the License, or (at your option) any later version.
  ~
  ~ This software is distributed in the hope that it will be useful,
  ~ but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
  ~ Lesser General Public License for more details.
  ~
  ~ You should have received a copy of the GNU Lesser General Public
  ~ License along with this software; if not, write to the Free
  ~ Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
  ~ 02110-1301 USA, or see the FSF site: http://www.fsf.org.
  -->
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-java-5.1.34-bin.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```
3.Copy here the jar MySql-Connector [Download MySql Connector Java](http://dev.mysql.com/downloads/connector/j/)  
4.In the file **JBOSS_HOME\standalone\configuration\standalone.xml** add or modify the next section.
```xml
<subsystem xmlns="urn:jboss:domain:datasources:1.0">
    <datasources>
        <datasource jta="true" jndi-name="java:/projectadmin_db" pool-name="projectadmin_db">
            <connection-url>jdbc:mysql://localhost:3306/projectadmin_db</connection-url>
            <driver>com.mysql</driver>
            <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
            <security>
                <user-name>root</user-name>
                <password>root</password>
            </security>
        </datasource>
        <drivers>
            <driver name="com.mysql" module="com.mysql">
                <driver-class>com.mysql.jdbc.Driver</driver-class>
                <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
            </driver>
        </drivers>
    </datasources>
</subsystem>
```

#Launching
Run server with followind program:**jboss-as-7.1.1.Final\bin\standalone.bat**  
Path rest **http://localhost:1313/ProjectAdminMng/rest**

#DataBase
Mysql:
User: **root**  
Pass: **root**  
Port: **127.0.0.1:3306**  
postgres: port 5432
pass:root
