package net.medsouz.tct.gui;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * @author medsouz
 * 
 */
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
		Minecraft.getMinecraft().renderEngine.bindTexture(image);
		GL11.glColor4f(r, g, b, opacity);
		drawQuad(posX, posY, width, height, 0, u, 0, v);
	}
	
	/**
	 * @param URL Image URL
	 * @return ResourceLocation containing the downloaded image
	 */
	public static ResourceLocation downloadImage(String URL) {
		return downloadImage(URL, null);
	}
	
	/**
	 * @param URL Image URL
	 * @param fallback Image to return if the download fails
	 * @return ResourceLocation containing the downloaded image
	 */
	public static ResourceLocation downloadImage(String URL, ResourceLocation fallback) {
		ResourceLocation r = new ResourceLocation(URL);
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		Object object = texturemanager.getTexture(r);
		if (object == null) {
			object = new ThreadDownloadImageData(null, URL, SkinManager.field_152793_a, null);
			texturemanager.loadTexture(r, (ITextureObject) object);
		}
		return r;

	}
	
	/**
	 * Draws an item on the screen
	 * @param id Item ID
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void drawItemIcon(int id, int x, int y, int width, int height) {
		Item i = Item.getItemById(id);
		IIcon icon = i.getIcon(new ItemStack(i), 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(i.getSpriteNumber()));
		drawQuad(x, y, width, height, icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV());
	}
	
	/**
	 * Draws a particle on the screen
	 * @param particleId Particle ID number, can usually be found in the EffectFX class of the particle.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void drawParticle(int particleId, int x, int y, int width, int height) {
		ResourceLocation pTex = new ResourceLocation("textures/particle/particles.png");
		float u = (particleId % 16) / 16.0f;
		float v = (particleId / 16) / 16.0f;
		Minecraft.getMinecraft().renderEngine.bindTexture(pTex);
		drawQuad(x, y, width, height, u, u + 0.0624375F, v, v + 0.0624375F);
	}
	
	public static void drawBlockSide(int id, int side, int x, int y, int width, int height) {
		drawBlockSide(id, side, x, y, width, height, 1, 1);
	}
	
	public static void drawBlockSide(int id, int side, int x, int y, int width, int height, float u, float v) {
		Block b = Block.getBlockById(id);
		IIcon icon = b.getBlockTextureFromSide(side);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/blocks/"+icon.getIconName()+".png"));
		drawQuad(x, y, width, height, 0, u, 0, v);
	}
	
	public static void drawQuad(int x, int y, int width, int height, float minU, float maxU, float minV, float maxV) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 0, minU, maxV);
		tessellator.addVertexWithUV(x + width, y + height, 0, maxU, maxV);
		tessellator.addVertexWithUV(x + width, y, 0, maxU, minV);
		tessellator.addVertexWithUV(x, y, 0, minU, minV);
		tessellator.draw();
	}
	
	public static void drawPlayer(String user, int x, int y, float scale){
		String skinurl = "http://s3.amazonaws.com/MinecraftSkins/"+user+".png";
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ModelBiped biped = new ModelBiped();
		biped.bipedCloak.isHidden = true;
		biped.bipedEars.isHidden = true;
		GL11.glPushMatrix();
			GL11.glTranslatef(x, y - (scale * 0.43f), 10);//has to have a z value > 10 or else the model gets half cut off
			GL11.glScalef(0.06f * scale, 0.06f * scale, 1);
			GL11.glRotatef(-20, 1, 0, 0);
			GL11.glRotatef(205, 0, 1, 0);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			Minecraft.getMinecraft().renderEngine.bindTexture(downloadImage(skinurl, AbstractClientPlayer.locationStevePng));
			for (int i = 0; i < biped.boxList.size(); i++) {
				((ModelRenderer) (biped.boxList.get(i))).render(1);
			}
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glPopMatrix();
	}
}
