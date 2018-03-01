# smartlog
author changlong chen，this is a project for log.
when you start you project，you should do this：LogFactory.startFrame(),
befor you stop you project you should do this:LogFactory.endFrame().
is easy to use smartlog for example:in class Student,
Log log = LogFactory.getLog(Student.class);
log.debug("hello","world"); or log.error("hello","world");
will write this into file:2018-3-1 0:54:24 helloworld! com.ccl.action.Student.speak(Student.java 10);
if you have any question please email 783905177@qq.com
thank you for read!
