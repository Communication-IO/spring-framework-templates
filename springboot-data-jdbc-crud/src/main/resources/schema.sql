--
-- Create table for `users`
--
CREATE TABLE `users` (
  `id` bigint(20) generated by default as identity NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
);