package com.example.day14.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int width = 100;
    int height = 50;

    //在内存中创建图片对象
    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

    //画图
    Graphics graphics = image.getGraphics();

    //背景色
    graphics.setColor(Color.PINK);
    graphics.fillRect(0,0,width,height);

    //边框
    graphics.setColor(Color.BLUE);
    graphics.drawRect(0,0,width-1,height-1);

    String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    //生成随机
    for(int i=1;i<=4;i++){
      Random random = new Random();
      int index = random.nextInt(str.length());
      String c = str.charAt(index)+"";
      graphics.drawString(c,width*i/5,height/2);
    }

    //画干扰线
    graphics.setColor(Color.GREEN);
    for(int i=1;i<=10;i++){
      Random random = new Random();

      int x1 = random.nextInt(width);
      int x2 = random.nextInt(width);
      int y1 = random.nextInt(height);
      int y2 = random.nextInt(height);

      graphics.drawLine(x1,y1,x2,y2);

    }


    //将图片输出到页面
    ImageIO.write(image,"jpg",resp.getOutputStream());

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
