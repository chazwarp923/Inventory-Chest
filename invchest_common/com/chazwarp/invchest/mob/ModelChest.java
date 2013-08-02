package com.chazwarp.invchest.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChest extends ModelBase
{
  //fields
    ModelRenderer Head_Bottom;
    ModelRenderer Head_Top;
    ModelRenderer Lock;
  
  public void Mob()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Head_Bottom = new ModelRenderer(this, 0, 40);
      Head_Bottom.addBox(-16F, -12F, -16F, 32, 24, 32);
      Head_Bottom.setRotationPoint(0F, 12F, 0F);
      Head_Bottom.setTextureSize(128, 128);
      Head_Bottom.mirror = true;
      setRotation(Head_Bottom, 0F, 0F, 0F);
      Head_Top = new ModelRenderer(this, 0, 0);
      Head_Top.addBox(-16F, -4F, -16F, 32, 8, 32);
      Head_Top.setRotationPoint(0F, -2F, 0F);
      Head_Top.setTextureSize(128, 128);
      Head_Top.mirror = true;
      setRotation(Head_Top, 0F, 0F, 0F);
      Lock = new ModelRenderer(this, 0, 0);
      Lock.addBox(-2F, -4F, -2F, 4, 8, 2);
      Lock.setRotationPoint(0F, 3F, -16F);
      Lock.setTextureSize(128, 128);
      Lock.mirror = true;
      setRotation(Lock, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Head_Bottom.render(f5);
    Head_Top.render(f5);
    Lock.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
