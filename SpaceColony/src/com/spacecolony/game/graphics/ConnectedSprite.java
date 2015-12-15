/*
 * Copyright (C) 2015 root
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.spacecolony.game.graphics;

import java.util.Arrays;
import org.newdawn.slick.Image;

/**
 *
 * @author root
 */
public class ConnectedSprite extends Sprite{

    public static final ConnectedSprite DEFAULT_TEST = new ConnectedSprite(SpriteSheet.TEST, 16);

    private Image[] images = new Image[47];

    public ConnectedSprite(SpriteSheet ss, int size) {
        super(ss, 0, 0, size, size);
        int rowNum = ss.getWidth() / size;

        int i = 0;
        int k = 0;
        while (i < images.length) {
            for (int j = 0; (j < rowNum) && i < images.length; j++) {
                images[i] = ss.getSubImage(j * size, k * size, size, size);
                i++;
            }
            k++;
        }
    }

    @Override
    public Image getImage(int meta) {
        return images[meta];
    }

    public static int getMetaFromNearTable(boolean[] near) {
        boolean tl = near[0];
        boolean tm = near[1];
        boolean tr = near[2];
        boolean ml = near[3];
        boolean mr = near[5];
        boolean bl = near[6];
        boolean bm = near[7];
        boolean br = near[8];

        if (tm) {
            if (bm) {
                if (mr) {
                    if (ml) {
                        if (tl) {
                            if (tr) {
                                if (bl) {
                                    if(br){
                                        return 46;
                                    }else{
                                        return 33;
                                    }
                                } else if (br) {
                                    return 34;
                                } else {
                                    return 29;
                                }
                            } else if (bl) {
                                if (br) {
                                    return 32;
                                } else {
                                    return 28;
                                }
                            } else if(br){
                                return 45;
                            }else {
                                return 41;
                            }
                        } else if (tr) {
                            if (br) {
                                if (bl) {
                                    return 35;
                                } else {
                                    return 30;
                                }
                            } else if(bl){
                                return 44;
                            }else {
                                return 42;
                            }
                        } else if (bl) {
                            if (br) {
                                return 31;
                            } else {
                                return 40;
                            }
                        } else if (br) {
                            return 43;
                        } else {
                            return 15;
                        }
                    } else {
                        if (tr) {
                            if (br) {
                                return 39;
                            } else {
                                return 23;
                            }
                        } else if (br) {
                            return 27;
                        } else {
                            return 14;
                        }
                    }
                } else if (ml) {
                    if (tl) {
                        if (bl) {
                            return 37;
                        } else {
                            return 25;
                        }
                    } else if (bl) {
                        return 21;
                    } else {
                        return 12;
                    }
                }
                return 5;
            } else if (mr) {
                if (ml) {
                    if (tr) {
                        if (tl) {
                            return 38;
                        } else {
                            return 26;
                        }
                    } else if (tl) {
                        return 22;
                    } else {
                        return 13;
                    }
                }
                if (tr) {
                    return 19;
                } else {
                    return 10;
                }
            } else if (ml) {
                if (tl) {
                    return 18;
                } else {
                    return 9;
                }
            }
            return 4;
        } else if (mr) {
            if (bm) {
                if (ml) {
                    if (bl) {
                        if (br) {
                            return 36;
                        } else {
                            return 24;
                        }
                    } else if (br) {
                        return 20;
                    } else {
                        return 11;
                    }
                }
                if (br) {
                    return 16;
                } else {
                    return 7;
                }
            } else if (ml) {
                return 6;
            }
            return 1;
        } else if (ml) {
            if (bm) {
                if (bl) {
                    return 17;
                } else {
                    return 8;
                }
            }
            return 2;
        } else if (bm) {
            return 3;
        }

        return 0;
    }

}
