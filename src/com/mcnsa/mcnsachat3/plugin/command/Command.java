package com.mcnsa.mcnsachat3.plugin.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.bukkit.command.CommandSender;

public interface Command {
	public Boolean handle(CommandSender player, String sArgs);

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface CommandInfo {
		public String alias();

		public String permission() default "";

		public String usage() default "";

		public String description() default "";

		public boolean visible() default true;
		
		public boolean playerOnly() default false;
	}
}
