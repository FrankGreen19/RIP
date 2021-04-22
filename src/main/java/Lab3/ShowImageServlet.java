package Lab3;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@WebServlet("/showImageServlet")
public class ShowImageServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "UPLOAD";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String imageName = request.getParameter("image");

        File imageFile = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY
                + File.separator + "image.jpg");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setContentType("image/jpeg");
        try(ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
