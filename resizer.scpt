FasdUAS 1.101.10   ��   ��    k             l      ��  ��    � �on run {browser, x, y, width, height}	tell application browser		set the bounds of the first window to {x, y, x + width, y + height}	end tellend run     � 	 	. o n   r u n   { b r o w s e r ,   x ,   y ,   w i d t h ,   h e i g h t }  	 t e l l   a p p l i c a t i o n   b r o w s e r  	 	 s e t   t h e   b o u n d s   o f   t h e   f i r s t   w i n d o w   t o   { x ,   y ,   x   +   w i d t h ,   y   +   h e i g h t }  	 e n d   t e l l  e n d   r u n   
  
 l     ��������  ��  ��        l      ��  ��    � �on run {browser, x, y, width, height}	tell application "System Events" to tell process "browser"		tell window 1			set size to {width, height}		end tell	end tellend run     �  Z o n   r u n   { b r o w s e r ,   x ,   y ,   w i d t h ,   h e i g h t }  	 t e l l   a p p l i c a t i o n   " S y s t e m   E v e n t s "   t o   t e l l   p r o c e s s   " b r o w s e r "  	 	 t e l l   w i n d o w   1  	 	 	 s e t   s i z e   t o   { w i d t h ,   h e i g h t }  	 	 e n d   t e l l  	 e n d   t e l l  e n d   r u n      l     ��������  ��  ��        l      ��  ��    � �on run argv	tell application "System Events" to tell process (item 1 of argv)		tell window 1			set size to {(item 2 of argv), (item 3 of argv)}		end tell	end tellend run     �  ^ o n   r u n   a r g v  	 t e l l   a p p l i c a t i o n   " S y s t e m   E v e n t s "   t o   t e l l   p r o c e s s   ( i t e m   1   o f   a r g v )  	 	 t e l l   w i n d o w   1  	 	 	 s e t   s i z e   t o   { ( i t e m   2   o f   a r g v ) ,   ( i t e m   3   o f   a r g v ) }  	 	 e n d   t e l l  	 e n d   t e l l  e n d   r u n      l     ��������  ��  ��        l      ��  ��    � �on run argv	tell application (item 1 of argv)		tell window 1			set size to {(item 2 of argv), (item 3 of argv)}		end tell	end tellend run     �   o n   r u n   a r g v  	 t e l l   a p p l i c a t i o n   ( i t e m   1   o f   a r g v )  	 	 t e l l   w i n d o w   1  	 	 	 s e t   s i z e   t o   { ( i t e m   2   o f   a r g v ) ,   ( i t e m   3   o f   a r g v ) }  	 	 e n d   t e l l  	 e n d   t e l l  e n d   r u n       l     ��������  ��  ��      !�� ! i      " # " I     �� $��
�� .aevtoappnull  �   � **** $ o      ���� 0 argv  ��   # O    ' % & % O    & ' ( ' O    % ) * ) r    $ + , + J      - -  . / . l    0���� 0 n     1 2 1 4    �� 3
�� 
cobj 3 m    ����  2 o    ���� 0 argv  ��  ��   /  4�� 4 l    5���� 5 n     6 7 6 4    �� 8
�� 
cobj 8 m    ����  7 o    ���� 0 argv  ��  ��  ��   , 1     #��
�� 
ptsz * 4    �� 9
�� 
cwin 9 m    ����  ( 4    �� :
�� 
prcs : l    ;���� ; n     < = < 4    
�� >
�� 
cobj > m    	����  = o    ���� 0 argv  ��  ��   & m      ? ?�                                                                                  sevs  alis    �  Macintosh HD               ��*�H+   è�System Events.app                                               Ƽ����        ����  	                CoreServices    ��*�      ���     è� è� è�  =Macintosh HD:System: Library: CoreServices: System Events.app   $  S y s t e m   E v e n t s . a p p    M a c i n t o s h   H D  -System/Library/CoreServices/System Events.app   / ��  ��       �� @ A��   @ ��
�� .aevtoappnull  �   � **** A �� #���� B C��
�� .aevtoappnull  �   � ****�� 0 argv  ��   B ���� 0 argv   C  ?��������
�� 
prcs
�� 
cobj
�� 
cwin
�� 
ptsz�� (� $*��k/E/ *�k/ ��l/��m/lv*�,FUUUascr  ��ޭ