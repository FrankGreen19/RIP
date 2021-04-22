package Lab4.wrapers;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;

public class MyHttpRequestWrapper extends ServletRequestWrapper {

    private class MyData {
        private String name;

        public MyData(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyData{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public MyHttpRequestWrapper(ServletRequest request) {
        super(request);
    }

    @Override
    public Object getAttribute(String name) {
        if (name.equals("mydata"))
            return new MyData("name");
        else
            return super.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object o) {
        super.setAttribute(name, o);
    }
}
