/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pebbleUI.fonts;

/**
 *
 * @author joshglendenning
 */
public interface FontLibrary {
	public boolean contains(String id);
	public Font get(String id);
}
