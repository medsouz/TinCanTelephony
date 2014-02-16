package net.medsouz.tct.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * @author medsouz
 * 
 */
public class RenderHelper {

	/**
	 * Render an image on the screen
	 * 
	 * @param image
	 *            ResourceLocation containing the image
	 * @param posX
	 *            X coordinate of the image
	 * @param posY
	 *            Y coordinate of the image
	 * @param width
	 *            Width of the image
	 * @param height
	 *            Height of the image
	 */
	public static void drawImage(ResourceLocation image, int posX, int posY, int width, int height) {
		drawImage(image, posX, posY, width, height, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}

	/**
	 * Render an image on the screen with opacity
	 * 
	 * @param image
	 *            ResourceLocation containing the image
	 * @param posX
	 *            X coordinate of the image
	 * @param posY
	 *            Y coordinate of the image
	 * @param width
	 *            Width of the image
	 * @param height
	 *            Height of the image
	 * @param opacity
	 *            Transparency of image
	 */
	public static void drawImage(ResourceLocation image, int posX, int posY, int width, int height, float opacity) {
		drawImage(image, posX, posY, width, height, 1.0f, 1.0f, 1.0f, opacity, 1.0f, 1.0f);
	}

	/**
	 * Render an image on the screen with custom colors and opacity
	 * 
	 * @param image
	 *            ResourceLocation containing the image
	 * @param posX
	 *            X coordinate of the image
	 * @param posY
	 *            Y coordinate of the image
	 * @param width
	 *            Width of the image
	 * @param height
	 *            Height of the image
	 * @param r
	 *            Red tint
	 * @param g
	 *            Green tint
	 * @param b
	 *            Blue tint
	 * @param opacity
	 *            Transparency of image
	 * @param u
	 *            U axis of texture
	 * @param v
	 *            V axis of texture
	 */
	public static void drawImage(ResourceLocation image, int posX, int posY, int width, int height, float r, float g, float b, float opacity, float u, float v) {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator tessellator = Tessellator.instance;
		Minecraft.getMinecraft().renderEngine.bindTexture(image);
		GL11.glColor4f(r, g, b, opacity);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(posX, posY + height, 0, 0, v);
		tessellator.addVertexWithUV(posX + width, posY + height, 0, u, v);
		tessellator.addVertexWithUV(posX + width, posY, 0, u, 0);
		tessellator.addVertexWithUV(posX, posY, 0, 0, 0);
		tessellator.draw();
	}
	
	/**
	 * @param URL Image URL
	 * @return ResourceLocation containing the downloaded image
	 */
	public static ResourceLocation downloadImage(String URL) {
		ResourceLocation r = new ResourceLocation(URL);
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(r);
        if (object == null)
        {
            object = new ThreadDownloadImageData(URL, null, null);
            texturemanager.loadTexture(r, (ITextureObject)object);
        }
		return r;
		
	}
}
